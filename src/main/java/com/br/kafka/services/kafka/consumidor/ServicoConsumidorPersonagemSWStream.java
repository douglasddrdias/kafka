package com.br.kafka.services.kafka.consumidor;

import com.br.kafka.dto.avro.personagem.PersonagemStarWars;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.br.kafka.ConstantesKafka.STORE_PERSONAGEM;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServicoConsumidorPersonagemSWStream {
    private final StreamsBuilderFactoryBean factoryBean;

    public PersonagemStarWars consultarPersonagem(int id) {
        var store = recuperarTabela();
        return store.get(id);
    }


    public List<PersonagemStarWars> listarPersonagens() {
        var store = recuperarTabela();
        List<PersonagemStarWars> personagens = new ArrayList<>();
        try (var cp = store.all()) {
            cp.forEachRemaining(p -> personagens.add(p.value));
        } catch (Exception e) {
            log.error("Erro ao listar todos os personagens: ", e);
        }
        return personagens;
    }

    private ReadOnlyKeyValueStore<Integer, PersonagemStarWars> recuperarTabela() {
        ReadOnlyKeyValueStore<Integer, PersonagemStarWars> store =
                factoryBean.getKafkaStreams().store(StoreQueryParameters.fromNameAndType(
                        STORE_PERSONAGEM, // nome do store interno
                        QueryableStoreTypes.keyValueStore()
                ));
        return store;
    }

}
