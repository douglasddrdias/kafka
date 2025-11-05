package com.br.kafka.utils;

import com.br.kafka.dto.esterno.swapi.SwapiResponse;

import java.util.List;

public final class GeradorPersonagensUtil {

    private GeradorPersonagensUtil() {
    }

    public static List<SwapiResponse> gerarPersonagens() {
        return List.of(
                new SwapiResponse("Luke Skywalker", "172", "77", "blond", "fair", "blue", "19BBY", "male", null),
                new SwapiResponse("C-3PO", "167", "75", "n/a", "gold", "yellow", "112BBY", "n/a", null),
                new SwapiResponse("R2-D2", "96", "32", "n/a", "white, blue", "red", "33BBY", "n/a", null),
                new SwapiResponse("Darth Vader", "202", "136", "none", "white", "yellow", "41.9BBY", "male", null),
                new SwapiResponse("Leia Organa", "150", "49", "brown", "light", "brown", "19BBY", "female", null),
                new SwapiResponse("Owen Lars", "178", "120", "brown, grey", "light", "blue", "52BBY", "male", null),
                new SwapiResponse("Beru Whitesun lars", "165", "75", "brown", "light", "blue", "47BBY", "female", null),
                new SwapiResponse("R5-D4", "97", "32", "n/a", "white, red", "red", "unknown", "n/a", null),
                new SwapiResponse("Biggs Darklighter", "183", "84", "black", "light", "brown", "24BBY", "male", null),
                new SwapiResponse("Obi-Wan Kenobi", "182", "77", "auburn, white", "fair", "blue-gray", "57BBY", "male", null)
        );
    }
}
