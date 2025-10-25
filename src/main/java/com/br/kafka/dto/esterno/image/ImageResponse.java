package com.br.kafka.dto.esterno.image;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageResponse(
        @JsonProperty("_id")
        String id,
        String name,
        String description,
        String image,
        @JsonProperty("__v")
        int version
) {
}
