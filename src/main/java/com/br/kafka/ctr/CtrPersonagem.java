package com.br.kafka.ctr;

import com.br.kafka.dto.personagem.PersonagemDTO;
import com.br.kafka.services.kafka.consumidor.ServicoConsumidorPersonagemSWStream;
import com.br.kafka.services.kafka.tradutor.TradutorPersonagem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("personagem")
@RequiredArgsConstructor
public class CtrPersonagem {
    private final ServicoConsumidorPersonagemSWStream servico;
    private final TradutorPersonagem tradutor;

    @GetMapping("{id}")
    public ResponseEntity<PersonagemDTO> getPersonagem(@PathVariable int id) {
        var dados = servico.consultarPersonagem(id);
        var personagem = tradutor.traduzirPersonagem(dados);
        return ResponseEntity.ok(personagem);
    }

    @GetMapping
    public ResponseEntity<List<PersonagemDTO>> listarPersonagens() {
        var dados = servico.listarPersonagens();
        var personagens = dados.stream().map(tradutor::traduzirPersonagem).toList();
        return ResponseEntity.ok(personagens);
    }
}
