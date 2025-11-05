package com.br.kafka.services.kafka.consumidor;

import com.br.kafka.dto.avro.personagem.PersonagemStarWars;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

import static com.br.kafka.ConstantesKafka.STORE_PERSONAGEM;

@Service
@RequiredArgsConstructor
public class ServicoConsumidorPersonagemSWStream {
    private final StreamsBuilderFactoryBean factoryBean;

    public PersonagemStarWars consultarPersonagem(int id) {
        ReadOnlyKeyValueStore<Integer, PersonagemStarWars> store =
                factoryBean.getKafkaStreams().store(StoreQueryParameters.fromNameAndType(
                        STORE_PERSONAGEM, // nome do store interno
                        QueryableStoreTypes.keyValueStore()
                ));
        return store.get(id);
    }
}
