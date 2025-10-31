package com.br.kafka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public enum GeneroEnum {
    HOMEM("male", "masculino"),
    MULHER("female", "feminino"),
    NA("", "não definido");
    private final String ingles;
    private final String traduzido;
    private static final List<GeneroEnum> GENEROS = List.of(values());

    public static GeneroEnum traduzir(String ingles) {
        return GENEROS.stream().filter(g -> g.ingles.equals(ingles)).findFirst().orElse(NA);
    }
}
