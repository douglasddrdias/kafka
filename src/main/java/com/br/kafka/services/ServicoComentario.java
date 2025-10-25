package com.br.kafka.services;

import com.br.kafka.dto.comentario.ComentarioDTO;
import com.br.kafka.enums.ComentarioEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServicoComentario {
    private static final Random RANDOM = new Random();

    public ComentarioDTO recuperarComentario(int id) {
        var quoteOpEnum = ComentarioEnum.recuperarPorId(id);
        if (quoteOpEnum.isPresent()) {
            var quotes = quoteOpEnum.get().getQuotes();
            var quote = quotes.get(RANDOM.nextInt(quotes.size()));
            var quoteData = new ComentarioDTO(quote, id);
            log.debug("Comentário: {}", quoteData);
            return quoteData;
        }
        return null;
    }
}
