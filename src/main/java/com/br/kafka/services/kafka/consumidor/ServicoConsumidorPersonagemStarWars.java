package com.br.kafka.services.kafka.consumidor;

import com.br.kafka.dto.avro.personagem.PersonagemStarWars;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServicoConsumidorPersonagemStarWars {
    public static final String MENSAGEM_AVRO_RECEBIDA = "📩 Mensagem Avro recebida!";
    public static final String NOME = "Nome: {}";
    public static final String ALTURA = "Altura: {}";
    public static final String GENERO = "Gênero: {}--";
    public static final String IMAGEM = "Imagem: {}";
    public static final String COMENTARIO = "Comentário: \"{}\" ";

    @KafkaListener(topics = "${starwars.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(PersonagemStarWars personagem, @Header(KafkaHeaders.OFFSET) long offset) {
        log.debug("Consumindo offset: {}", offset);
        log.debug(MENSAGEM_AVRO_RECEBIDA);
        log.debug(NOME, personagem.getNome());
        log.debug(ALTURA, personagem.getAltura());
        log.debug(GENERO, personagem.getGenero());
        log.debug(IMAGEM, personagem.getUrlImagem());
        log.debug(COMENTARIO, personagem.getComentario());
    }

}
