package com.br.kafka.services;

import com.br.kafka.dto.avro.personagem.PersonagemStarWars;
import com.br.kafka.services.esterno.ServicoImagem;
import com.br.kafka.services.esterno.ServicoSwapi;
import com.br.kafka.services.kafka.consumidor.ServicoConsumidorPersonagemStarWars;
import com.br.kafka.services.kafka.produtor.ServicoProdutorPersonagemStarWars;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServicoPersonagemSW {
    private final ServicoProdutorPersonagemStarWars produtor;
    private final ServicoConsumidorPersonagemStarWars consumidor;
    private final ServicoSwapi servicoSwapi;
    private final ServicoImagem servicoImagem;
    private final ServicoComentario servicoComentario;

    public void processarPersonagem(int id) {
        var personagem = servicoSwapi.recuperarPersonagem(id);
        var imagem = servicoImagem.recuperarImagemUrl(personagem.nome());
        var comentario = servicoComentario.recuperarComentario(id);
        var personagemStarWars = PersonagemStarWars.newBuilder()
                .setId(id)
                .setNome(personagem.nome())
                .setAltura(personagem.altura())
                .setGenero(personagem.genero())
                .setUrlImagem(imagem.image())
                .setComentario(comentario.comentario())
                .build();
        produtor.enviarPersonagem(personagemStarWars);
    }
}
