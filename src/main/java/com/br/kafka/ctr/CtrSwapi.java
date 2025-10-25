package com.br.kafka.ctr;

import com.br.kafka.dto.esterno.swapi.SwapiResponse;
import com.br.kafka.services.esterno.ServicoSwapi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swapi")
@RequiredArgsConstructor
public class CtrSwapi {
    private final ServicoSwapi service;

    @GetMapping("{id}")
    public ResponseEntity<SwapiResponse> getCharacter(@PathVariable int id) {
        return ResponseEntity.ok(service.recuperarPersonagem(id));
    }
}
