package com.br.kafka.ctr;

import com.br.kafka.dto.quote.QuoteResponse;
import com.br.kafka.services.external.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quote")
@RequiredArgsConstructor
public class QuoteCtr {
    private final QuoteService service;

    @GetMapping("{id}")
    public ResponseEntity<QuoteResponse> getQuote(@PathVariable int id) {
        return ResponseEntity.ok(service.getQuote(id));
    }
}
