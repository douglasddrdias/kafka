package com.br.kafka.dto.esterno.swapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SwapiResponse(
        String name,
        String height,
        String mass,
        @JsonProperty("hair_color")
        String hairColor,
        @JsonProperty("skin_color")
        String skinColor,
        @JsonProperty("eye_color")
        String eyeColor,
        @JsonProperty("birth_year")
        String birthYear,
        String gender
) {
}
