package com.br.kafka.dto.esterno.swapi;

import java.util.Collection;

public record PaginaSwapiResponse(
        int count,
        String next,
        Collection<SwapiResponse> results
) {
}
