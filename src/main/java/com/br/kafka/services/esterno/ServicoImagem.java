package com.br.kafka.services.esterno;

import com.br.kafka.dto.esterno.image.ImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServicoImagem {
    private final RestTemplate restTemplate;
    @Value("${image.url}")
    private String url;

    public ImageResponse recuperarImagemUrl(String nome) {
        log.debug("Recuperando a imagem do personagem: {}", nome);
        var imageResponses = restTemplate.exchange(
                url + nome,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ImageResponse>>() {
                }
        ).getBody();
        if (imageResponses != null) {
            var imageResponse = imageResponses.stream().findFirst().orElse(null);
            log.debug("Dados da imagem: {}", imageResponse);
            return imageResponse;
        }
        return null;
    }
}
