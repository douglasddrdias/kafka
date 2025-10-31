package com.br.kafka.services;

import com.br.kafka.dto.esterno.swapi.SwapiResponse;
import com.br.kafka.utils.GeradorPersonagensUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServicoTraducaoSwApiTest {

    private ServicoTraducaoSwApi servico;
    private static final String MSG_COR_CABELOS = "Não foi encontrada correspondência para cor de cabelos de: %s";
    private static final String MSG_COR_OLHOS = "Não foi encontrada correspondência para cor de olhos de: %s";
    private static final String MSG_COR_PELE = "Não foi encontrada correspondência para cor de pele de: %s";
    private static final String NA = "n/a";

    @BeforeEach
    void setUp() {
        servico = new ServicoTraducaoSwApi();
    }

    @Test
    void deveTraduzirLukeSkywalkerCorretamente() {
        var luke = GeradorPersonagensUtil.gerarPersonagens().getFirst();

        var dto = servico.traduzir(luke);

        assertNotNull(dto);
        assertEquals("Luke Skywalker", dto.nome());
        assertEquals("172", dto.altura());
        assertEquals("77", dto.peso());
        assertEquals("loiro", dto.corCabelo());
        assertEquals("pele clara", dto.corPele());
        assertEquals("azul", dto.corOlhos());
        assertEquals("19BBY", dto.anoNascimento());
        assertEquals("masculino", dto.genero());
    }

    @Test
    void deveRetornarNaoSemCorrespondenciaQuandoCorNaoExistir() {
        var personagem = new SwapiResponse("Teste", "100", "50", "unknownColor", "weirdTone", "crazyEye", "0BBY", "other");
        var dto = servico.traduzir(personagem);
        assertEquals(ServicoTraducaoSwApi.SEM_CORRESPONDENCIA, dto.corCabelo());
        assertEquals(ServicoTraducaoSwApi.SEM_CORRESPONDENCIA, dto.corPele());
        assertEquals(ServicoTraducaoSwApi.SEM_CORRESPONDENCIA, dto.corOlhos());
    }

    @Test
    void deveRetornarNullQuandoEntradaForNull() {
        assertNull(servico.traduzir(null));
    }

    @Test
    void deveTraduzirTodosOsPersonagensSemErro() {
        List<SwapiResponse> personagens = GeradorPersonagensUtil.gerarPersonagens();

        for (SwapiResponse p : personagens) {
            var dto = servico.traduzir(p);
            if (diferenteNA(p.hairColor())) {
                assertNotEquals(ServicoTraducaoSwApi.SEM_CORRESPONDENCIA, dto.corCabelo(), String.format(MSG_COR_CABELOS, p.hairColor()));
            }
            if (diferenteNA(p.skinColor())) {
                assertNotEquals(ServicoTraducaoSwApi.SEM_CORRESPONDENCIA, dto.corPele(), String.format(MSG_COR_PELE, p.skinColor()));
            }
            if (diferenteNA(p.eyeColor())) {
                assertNotEquals(ServicoTraducaoSwApi.SEM_CORRESPONDENCIA, dto.corOlhos(), String.format(MSG_COR_OLHOS, p.eyeColor()));
            }
        }
    }

    private boolean diferenteNA(String valor) {
        return !NA.equalsIgnoreCase(valor);
    }
}