package com.br.kafka.services.kafka.tradutor;

import com.br.kafka.dto.avro.personagem.PersonagemStarWars;
import com.br.kafka.dto.personagem.PersonagemDTO;
import org.springframework.stereotype.Component;

@Component
public class TradutorPersonagem {
    public PersonagemDTO traduzirPersonagem(PersonagemStarWars dados) {
        return PersonagemDTO.builder()
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
    }
}
