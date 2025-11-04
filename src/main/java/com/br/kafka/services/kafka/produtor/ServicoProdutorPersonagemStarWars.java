package com.br.kafka.services.kafka.produtor;

import com.br.kafka.dto.avro.personagem.PersonagemStarWars;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServicoProdutorPersonagemStarWars {
    public static final String ENVIANDO_PERSONAGEM = "Enviando personagem: {}";
    public static final String MSG_PUBLICADA_SUCESSO = "Mensagem Avro publicada com sucesso! Offset: {} | Partition: {}";
    public static final String MSG_ERRO_PUBLICAR = "Erro ao publicar mensagem Avro: --{}";
    private final KafkaTemplate<Integer, PersonagemStarWars> kafkaTemplate;
    @Value("${starwars.topic}")
    private String topic;

    public void enviarPersonagem(PersonagemStarWars personagem) {
        log.debug(ENVIANDO_PERSONAGEM, personagem);
        var future = kafkaTemplate.send(topic, personagem.getId(), personagem);
        future.whenComplete(this::confirmacaoPublicacaoPersonagem);
    }

    public void confirmacaoPublicacaoPersonagem(SendResult<Integer, PersonagemStarWars> dadosPublicacao, Throwable ex) {
        if (ex == null) {
            log.debug(MSG_PUBLICADA_SUCESSO,
                    dadosPublicacao.getRecordMetadata().offset(),
                    dadosPublicacao.getRecordMetadata().partition());
        } else {
            log.error(MSG_ERRO_PUBLICAR, ex.getMessage());
        }
    }
}
