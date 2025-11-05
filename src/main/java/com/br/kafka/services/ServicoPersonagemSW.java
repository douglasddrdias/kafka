package com.br.kafka.services;

import com.br.kafka.dto.avro.personagem.PersonagemStarWars;
import com.br.kafka.dto.esterno.swapi.SwapiResponse;
import com.br.kafka.dto.swapi.PersonagemDTO;
import com.br.kafka.services.esterno.ServicoImagem;
import com.br.kafka.services.esterno.ServicoSwapi;
import com.br.kafka.services.kafka.produtor.ServicoProdutorPersonagemStarWars;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServicoPersonagemSW {
    private final ServicoProdutorPersonagemStarWars produtor;
    private final ServicoSwapi servicoSwapi;
    private final ServicoImagem servicoImagem;
    private final ServicoComentario servicoComentario;
    private final ServicoTraducaoSwApi traducaoSwApi;

    public void processarPersonagem(int id) {
        var personagem = servicoSwapi.recuperarPersonagem(id);
        processarPersonagem(id, personagem);
    }

    public void processarTodosPersonagens() {
        String url = null;
        do {
            var pagina = servicoSwapi.recuperarPersonagensPaginados(url);
            log.info("Proxima: {}", pagina.next());
            url = pagina.next();
            pagina.results().forEach(this::processarPersonagemPaginado);
        } while (url != null);
    }

    public void processarPersonagemPaginado(SwapiResponse swapiResponse) {
        var id = swapiResponse.url() != null ? Integer.parseInt(swapiResponse.url().split("/")[swapiResponse.url().split("/").length - 1]) : 0;
        var personagem = traducaoSwApi.traduzir(swapiResponse);
        processarPersonagem(id, personagem);
    }

    public void processarPersonagem(int id, PersonagemDTO personagem) {
        var imagem = servicoImagem.recuperarImagemUrl(personagem.nome());
        var comentario = servicoComentario.recuperarComentario(id);
        var url = imagem != null ? imagem.image() : null;
        var frase = comentario != null ? comentario.comentario() : null;
        var personagemStarWars = PersonagemStarWars.newBuilder()
                .setId(id)
                .setNome(personagem.nome())
                .setAltura(personagem.altura())
                .setGenero(personagem.genero())
                .setUrlImagem(url)
                .setComentario(frase)
                .setCorCabelo(personagem.corCabelo())
                .setCorPele(personagem.corPele())
                .setCorOlhos(personagem.corOlhos())
                .setAnoNascimento(personagem.anoNascimento())
                .build();
        produtor.enviarPersonagem(personagemStarWars);
    }
}
