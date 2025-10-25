package com.br.kafka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum ComentarioEnum {
    OBIWAN(10, List.of(
            "Você deve fazer o que acha certo, claro.",
            "Porto Espacial de Mos Eisley. Você nunca encontrará um antro mais miserável de escória e vilania. Devemos ser cautelosos.",
            "Seus olhos podem enganá-lo. Não confie neles.",
            "Lembre-se... a Força estará com você, sempre.",
            "Na minha experiência, não existe sorte.",
            "Estes não são os droids que você está procurando.",
            "Eu senti uma grande perturbação na Força, como se milhões de vozes de repente gritassem em terror e fossem subitamente silenciadas.",
            "Use a Força, Luke.",
            "Você não pode vencer, Darth. Se você me derrubar, me tornarei mais poderoso do que você poderia imaginar.",
            "Isso não é uma lua. É uma estação espacial.",
            "Luke! Não ceda ao ódio. Isso leva ao Lado Sombrio.",
            "Quem é mais tolo, o tolo ou o tolo que o segue?",
            "E esses pontos de impacto, muito precisos para serem de povos da areia. Só os Stormtroopers Imperiais são tão precisos."
    )),
    DATH_VADER(4, List.of(
            "Eu acho sua falta de fé perturbadora.",
            "O círculo agora está completo. Quando o deixei, eu era apenas um aprendiz. Agora sou o mestre.",
            "A Força está com você, jovem Skywalker, mas você ainda não é um Jedi.",
            "Não. Eu sou seu pai.",
            "Impressionante. Mais impressionante.",
            "Estou alterando o acordo. Reze para que eu não o altere ainda mais.",
            "Você subestima o poder do Lado Sombrio. Se você não lutar, então você encontrará seu destino.",
            "Você me falhou pela última vez, Almirante!",
            "A Força é forte neste.",
            "A capacidade de destruir um planeta é insignificante perto do poder da Força."
    )),
    YODA(20, List.of(
            "Faça. Ou não faça. Não existe tentar.",
            "Desaprender o que você aprendeu, você deve.",
            "Quando novecentos anos você atingir, tão bem parecer você não irá.",
            "Verdadeiramente maravilhosa, a mente de uma criança é.",
            "Um Jedi usa a Força para conhecimento e defesa, nunca para ataque.",
            "Aventura. Emoção. Um Jedi não anseia por estas coisas.",
            "Tamanho não importa. Pelo meu tamanho me julga você?",
            "O medo é o caminho para o lado sombrio… o medo leva à raiva… a raiva leva ao ódio… o ódio leva ao sofrimento.",
            "Guerras não fazem alguém grande.",
            "Seres de luz somos nós… não esta matéria bruta.",
            "Difícil de ver. Sempre em movimento é o futuro.",
            "Controle, controle, aprender a controlar você deve!"
    )),
    LUKE(1, List.of(
            "Eu nunca me voltarei para o Lado Sombrio. Você falhou, Vossa Alteza. Eu sou um Jedi, como meu pai antes de mim.",
            "Se existe um centro brilhante para o universo, você está no planeta mais distante dele.",
            "Eu sou Luke Skywalker. Estou aqui para resgatá-la.",
            "A Força é forte na minha família. Meu pai a tem. Eu a tenho. Minha irmã a tem. Você também tem esse poder.",
            "Mas eu ia até a Estação Tosche pegar uns conversores de energia!"
    )),
    HAN_SOLO(14, List.of(
            "Chewie... estamos em casa.",
            "Nunca me diga as probabilidades.",
            "As mulheres sempre descobrem a verdade. Sempre.",
            "Nave rápida? Você nunca ouviu falar da Millennium Falcon? É a nave que fez a Rota de Kessel em menos de doze parsecs.",
            "Ótimo tiro, garoto, foi um em um milhão!",
            "Conversa chata de qualquer maneira. LUKE, VAMOS TER COMPANHIA!",
            "Nenhuma recompensa vale por isso.",
            "Garota fantástica. Ou eu vou matá-la ou estou começando a gostar dela.",
            "Olha, Sua Venerabilidade, vamos deixar uma coisa clara. Eu só recebo ordens de uma pessoa: de mim."
    )),
    LEIA(6, List.of(
            "Ajude-me, Obi-Wan Kenobi. Você é minha única esperança.",
            "Você não é um pouco baixo para um stormtrooper?",
            "Alguém tem que salvar a nossa pele. Pelo duto de lixo, piloto.",
            "Alguém pode tirar este carpete ambulante do meu caminho?",
            "Você não precisa se preocupar com sua recompensa. Se dinheiro é tudo que você ama, então é isso que você receberá."
    ));

    private final int id;
    private final List<String> quotes;

    public static Optional<ComentarioEnum> recuperarPorId(int id) {
        return Stream.of(values())
                .filter(q -> q.id == id)
                .findFirst();
    }

}
