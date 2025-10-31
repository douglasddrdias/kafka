package com.br.kafka.dto.swapi;

import lombok.Builder;

@Builder
public record PersonagemDTO(
        String nome,
        String altura,
        String peso,
        String corCabelo,
        String anoNascimento,
        String genero,
        String corPele,
        String corOlhos
) {
}
