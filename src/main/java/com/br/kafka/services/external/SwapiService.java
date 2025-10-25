package com.br.kafka.services.external;

import com.br.kafka.dto.swapi.SwapiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class SwapiService {
    private final RestTemplate restTemplate;
    @Value("${swapi.url}")
    private String url;

    public SwapiResponse getCharacterData(int id) {
        log.debug("Searching data of character ID: {}", id);
        var swapiData = restTemplate.getForObject(
                url + id,
                SwapiResponse.class
        );
        log.debug("Data of character: {}", swapiData);
        return swapiData;
    }
}
