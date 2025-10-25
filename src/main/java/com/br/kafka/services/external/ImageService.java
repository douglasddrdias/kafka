package com.br.kafka.services.external;

import com.br.kafka.dto.image.ImageResponse;
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
public class ImageService {
    private final RestTemplate restTemplate;
    @Value("${image.url}")
    private String url;

    public ImageResponse getImageUrl(String name) {
        log.debug("Searching image of character Name: {}", name);
        var imageResponses = restTemplate.exchange(
                url + name,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ImageResponse>>() {
                }
        ).getBody();
        if (imageResponses != null) {
            var imageResponse = imageResponses.stream().findFirst().orElse(null);
            log.debug("Data of image: {}", imageResponse);
            return imageResponse;
        }
        return null;
    }
}
