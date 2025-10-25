package com.br.kafka.ctr;

import com.br.kafka.dto.esterno.image.ImageResponse;
import com.br.kafka.services.esterno.ServicoImagem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("imagem")
public class CtrImagem {
    private final ServicoImagem service;

    @GetMapping("{nome}")
    public ResponseEntity<ImageResponse> getImage(@PathVariable String nome) {
        return ResponseEntity.ok(service.recuperarImagemUrl(nome));
    }
}
