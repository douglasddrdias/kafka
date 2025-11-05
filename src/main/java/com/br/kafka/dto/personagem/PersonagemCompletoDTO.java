package com.br.kafka.dto.personagem;

import lombok.Builder;

@Builder
public record PersonagemCompletoDTO(
        int id,
        String nome,
        String altura,
        String peso,
        String corCabelo,
        String anoNascimento,
        String genero,
        String corPele,
        String corOlhos,
        String urlImagem
) {

}
