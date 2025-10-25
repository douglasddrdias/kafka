package com.br.kafka.dto.quote;

public record QuoteResponse(
        String quote,
        int author
) {
}
