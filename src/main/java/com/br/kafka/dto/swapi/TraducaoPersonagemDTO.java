package com.br.kafka.dto.swapi;

import lombok.Builder;

@Builder
public record TraducaoPersonagemDTO(
        String nome,
        String altura,
        String peso,
        String corCabelo,
        String anoNascimento,
        String genero,
        String corPele,
        String corOlhos,
        String url
) {
}
