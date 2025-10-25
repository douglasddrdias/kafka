package com.br.kafka.ctr;

import com.br.kafka.dto.swapi.SwapiResponse;
import com.br.kafka.services.external.SwapiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swapi")
@RequiredArgsConstructor
public class SwapiCtr {
    private final SwapiService service;

    @GetMapping("{id}")
    public ResponseEntity<SwapiResponse> getCharacter(@PathVariable int id) {
        return ResponseEntity.ok(service.getCharacterData(id));
    }
}
