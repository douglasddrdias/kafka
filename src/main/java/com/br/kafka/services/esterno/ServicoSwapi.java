package com.br.kafka.services.esterno;

import com.br.kafka.dto.esterno.swapi.SwapiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServicoSwapi {
    private final RestTemplate restTemplate;
    @Value("${swapi.url}")
    private String url;

    public SwapiResponse recuperarPersonagem(int id) {
        log.debug("Recuperando dados do personagem id: {}", id);
        var swapiData = restTemplate.getForObject(
                url + id,
                SwapiResponse.class
        );
        log.debug("Dados do personagem: {}", swapiData);
        return swapiData;
    }
}
