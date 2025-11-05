package com.br.kafka.ctr;

import com.br.kafka.dto.swapi.PersonagemDTO;
import com.br.kafka.services.ServicoPersonagemSW;
import com.br.kafka.services.esterno.ServicoSwapi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swapi")
@RequiredArgsConstructor
public class CtrSwapi {
    private final ServicoSwapi service;
    private final ServicoPersonagemSW personagemSW;

    @GetMapping("{id}")
    public ResponseEntity<PersonagemDTO> getCharacter(@PathVariable int id) {
        return ResponseEntity.ok(service.recuperarPersonagem(id));
    }

    @GetMapping("publicar-dados-completos/{id}")
    public ResponseEntity<String> publicarDadosCompletos(@PathVariable int id) {
        personagemSW.processarPersonagem(id);
        return ResponseEntity.ok("Dados publicados com sucesso");
    }

    @GetMapping
    public ResponseEntity<String> recuperarDadosCompletos() {
        personagemSW.processarTodosPersonagens();
        return ResponseEntity.ok("Dados publicados com sucesso");
    }
}
