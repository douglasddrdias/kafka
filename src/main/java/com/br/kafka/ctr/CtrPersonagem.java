package com.br.kafka.ctr;

import com.br.kafka.dto.personagem.PersonagemCompletoDTO;
import com.br.kafka.services.kafka.consumidor.ServicoConsumidorPersonagemSWStream;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personagem")
@RequiredArgsConstructor
public class CtrPersonagem {
    private final ServicoConsumidorPersonagemSWStream servico;

    @GetMapping("{id}")
    public ResponseEntity<PersonagemCompletoDTO> getPersonagem(@PathVariable int id) {
        var dados = servico.consultarPersonagem(id);
        var personagem = PersonagemCompletoDTO.builder()
                .id(dados.getId())
                .nome(dados.getNome())
                .altura(dados.getAltura())
                .peso(dados.getPeso())
                .corCabelo(dados.getCorCabelo())
                .anoNascimento(dados.getAnoNascimento())
                .genero(dados.getGenero())
                .corPele(dados.getCorPele())
                .corOlhos(dados.getCorOlhos())
                .urlImagem(dados.getUrlImagem())
                .build();
        return ResponseEntity.ok(personagem);
    }
}
