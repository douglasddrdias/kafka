package com.br.kafka.ctr;

import com.br.kafka.dto.image.ImageResponse;
import com.br.kafka.services.external.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("image")
public class ImageCtr {
    private final ImageService service;

    @GetMapping("{name}")
    public ResponseEntity<ImageResponse> getImage(@PathVariable String name) {
        return ResponseEntity.ok(service.getImageUrl(name));
    }
}
