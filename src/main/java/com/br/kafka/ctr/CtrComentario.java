package com.br.kafka.ctr;

import com.br.kafka.dto.comentario.ComentarioDTO;
import com.br.kafka.services.ServicoComentario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comentario")
@RequiredArgsConstructor
public class CtrComentario {
    private final ServicoComentario service;

    @GetMapping("{id}")
    public ResponseEntity<ComentarioDTO> getQuote(@PathVariable int id) {
        return ResponseEntity.ok(service.recuperarComentario(id));
    }
}
