package com.br.kafka.services;

import com.br.kafka.dto.esterno.swapi.SwapiResponse;
import com.br.kafka.dto.swapi.PersonagemDTO;
import com.br.kafka.enums.GeneroEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class ServicoTraducaoSwApi {
    private static final Map<String, String> TRADUCAO_CABELOS = gerarTraducaoCabelos();
    private static final Map<String, String> TRADUCAO_OLHOS = gerarTraducaoOlhos();
    private static final Map<String, String> TRADUCAO_PELE = gerarTraducaoPele();
    public static final String NA = "não definido";
    public static final String SEM_CORRESPONDENCIA = "sem correspondência";
    public static final List<String> NAO_DEFINIDOS = List.of("none", "n/a");
    public static final String COR_SEM_CORRESPONDENCIA = "Não foi encontrada correspondência de cor de {} para cor: '{}'";
    public static final String CABELO = "cabelo";
    public static final String PELE = "pele";
    public static final String OLHO = "olho";
    public static final String VIRGULA = ",";
    public static final String VAZIO = "";
    public static final String VIRGULA_ESPACO = ", ";

    private static Map<String, String> gerarTraducaoCabelos() {
        Map<String, String> coresCabelo = new HashMap<>();
        // Mapeamento das cores de cabelo
        coresCabelo.put("black", "preto");
        coresCabelo.put("brown", "castanho");
        coresCabelo.put("blonde", "loiro");
        coresCabelo.put("blond", "loiro");
        coresCabelo.put("red", "ruivo");
        coresCabelo.put("auburn", "castanho-avermelhado");
        coresCabelo.put("chestnut", "castanho");
        coresCabelo.put("dark brown", "castanho escuro");
        coresCabelo.put("light brown", "castanho claro");
        coresCabelo.put("dirty blonde", "loiro sujo");
        coresCabelo.put("platinum blonde", "loiro platina");
        coresCabelo.put("strawberry blonde", "loiro morango");
        coresCabelo.put("ginger", "ruivo");
        coresCabelo.put("brunette", "moreno");
        coresCabelo.put("jet black", "preto intenso");
        coresCabelo.put("ash brown", "castanho cinza");
        coresCabelo.put("honey blonde", "loiro mel");
        coresCabelo.put("golden blonde", "loiro dourado");
        coresCabelo.put("silver", "prateado");
        coresCabelo.put("gray", "grisalho");
        coresCabelo.put("white", "branco");
        coresCabelo.put("bald", "careca");
        coresCabelo.put("blue", "azul");
        coresCabelo.put("grey", "cinza");
        return coresCabelo;
    }

    private static Map<String, String> gerarTraducaoOlhos() {
        Map<String, String> coresOlhos = new HashMap<>();
        // Mapeamento das cores de olhos
        coresOlhos.put("brown", "castanho");
        coresOlhos.put("hazel", "avelã");
        coresOlhos.put("green", "verde");
        coresOlhos.put("blue", "azul");
        coresOlhos.put("gray", "cinza");
        coresOlhos.put("grey", "cinza");
        coresOlhos.put("amber", "âmbar");
        coresOlhos.put("black", "preto");
        coresOlhos.put("dark brown", "castanho escuro");
        coresOlhos.put("light brown", "castanho claro");
        coresOlhos.put("blue-green", "azul-esverdeado");
        coresOlhos.put("green-blue", "verde-azulado");
        coresOlhos.put("gray-blue", "azul-cinza");
        coresOlhos.put("honey", "mel");
        coresOlhos.put("violet", "violeta");
        coresOlhos.put("red", "vermelho");
        coresOlhos.put("heterochromia", "heterocromia");
        coresOlhos.put("albino", "albino");
        coresOlhos.put("yellow", "amarelo");
        coresOlhos.put("gold", "dourado");
        coresOlhos.put("blue-gray", "azul acinzentado");
        return coresOlhos;
    }

    private static Map<String, String> gerarTraducaoPele() {
        Map<String, String> coresPele = new HashMap<>();
        // Mapeamento dos tons de pele
        coresPele.put("fair", "pele clara");
        coresPele.put("white", "pele clara");
        coresPele.put("light", "pele clara");
        coresPele.put("pale", "pele pálida");
        coresPele.put("ivory", "pele marfim");
        coresPele.put("porcelain", "pele porcelana");
        coresPele.put("cream", "pele creme");
        coresPele.put("beige", "pele bege");
        coresPele.put("olive", "pele oliva");
        coresPele.put("tan", "pele bronzeada");
        coresPele.put("golden", "pele dourada");
        coresPele.put("medium", "pele morena clara");
        coresPele.put("bronze", "pele bronze");
        coresPele.put("caramel", "pele caramelo");
        coresPele.put("honey", "pele mel");
        coresPele.put("brown", "pele morena");
        coresPele.put("dark brown", "pele morena escura");
        coresPele.put("deep brown", "pele marrom profundo");
        coresPele.put("ebony", "pele ébano");
        coresPele.put("dark", "pele escura");
        coresPele.put("black", "pele negra");
        coresPele.put("mahogany", "pele mogno");
        coresPele.put("cocoa", "pele cacau");
        coresPele.put("almond", "pele amêndoa");
        coresPele.put("peach", "pele pêssego");
        coresPele.put("rosy", "pele rosada");
        coresPele.put("ruddy", "pele ruborizada");
        coresPele.put("sallow", "pele amarelada");
        coresPele.put("gold", "dourado");
        coresPele.put("blue", "azul");
        coresPele.put("red", "vermelha");
        return coresPele;
    }

    public PersonagemDTO traduzir(SwapiResponse dados) {
        if (dados != null) {
            var b = PersonagemDTO.builder();
            b.nome(dados.name());
            b.altura(dados.height());
            b.peso(dados.mass());
            b.corCabelo(traduzir(TRADUCAO_CABELOS, dados.hairColor(), CABELO));
            b.anoNascimento(dados.birthYear());
            b.genero(traducaoGenero(dados.gender()));
            b.corPele(traduzir(TRADUCAO_PELE, dados.skinColor(), PELE));
            b.corOlhos(traduzir(TRADUCAO_OLHOS, dados.eyeColor(), OLHO));
            b.url(dados.url());
            return b.build();
        }
        return null;
    }

    private String traduzir(Map<String, String> mapTraducao, String valor, String assuntoTraducao) {
        if (NAO_DEFINIDOS.contains(valor)) {
            return NA;
        }
        if (!valor.contains(VIRGULA)) {
            var cor = mapTraducao.get(valor);
            if (cor == null) {
                cor = SEM_CORRESPONDENCIA;
                log.info(COR_SEM_CORRESPONDENCIA, assuntoTraducao, valor);
            }
            return cor;
        }
        return processaLista(valor, cor -> traduzir(mapTraducao, cor, assuntoTraducao));
    }

    private String processaLista(String valor, Function<String, String> funcao) {
        return valor
                .lines()
                .flatMap(l -> Arrays.stream(l.split(VIRGULA)))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(funcao)
                .reduce((a, b) -> a + VIRGULA_ESPACO + b)
                .orElse(VAZIO);
    }

    private String traducaoGenero(String genero) {
        return GeneroEnum.traduzir(genero).getTraduzido();
    }
}
