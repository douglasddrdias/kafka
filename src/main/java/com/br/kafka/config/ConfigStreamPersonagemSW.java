package com.br.kafka.config;

import com.br.kafka.dto.avro.personagem.PersonagemStarWars;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.br.kafka.ConstantesKafka.SCHEMA_REGISTRY_URL;
import static com.br.kafka.ConstantesKafka.STORE_PERSONAGEM;

@Configuration
@Slf4j
@EnableKafkaStreams
@EnableKafka
public class ConfigStreamPersonagemSW {


    @Value("${spring.kafka.consumer.properties.schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${starwars.topic}")
    private String topic;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.streams.application-id}")
    private String applicationId;

    @Bean
    public KTable<Integer, PersonagemStarWars> personagemTable(StreamsBuilder builder) {

        SpecificAvroSerde<PersonagemStarWars> personagemSerde = criarSerdePersonagem();

        // cria a tabela a partir do tópico
        KTable<Integer, PersonagemStarWars> tabela = builder.table(
                topic,
                Consumed.with(Serdes.Integer(), personagemSerde),
                Materialized.as(STORE_PERSONAGEM)
        );

        // só pra debug/log
        tabela.toStream().foreach((id, p) -> {
            log.info("🛰️ Personagem atualizado -> ID: {} Nome: {}", id, p.getNome());
        });

        return tabela;
    }

    private SpecificAvroSerde<PersonagemStarWars> criarSerdePersonagem() {
        var serdeConfig =
                Collections.singletonMap(SCHEMA_REGISTRY_URL, schemaRegistryUrl);

        SpecificAvroSerde<PersonagemStarWars> personagemSerde = new SpecificAvroSerde<>();
        personagemSerde.configure(serdeConfig, false); // false = value, true = key
        return personagemSerde;
    }


    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        props.put(SCHEMA_REGISTRY_URL, schemaRegistryUrl);

        return new KafkaStreamsConfiguration(props);
    }

}
