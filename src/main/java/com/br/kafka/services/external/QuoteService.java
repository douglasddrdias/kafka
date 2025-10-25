package com.br.kafka.services.external;

import com.br.kafka.dto.quote.QuoteResponse;
import com.br.kafka.enums.QuoteEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuoteService {
    @Value("${quote.url}")
    private String url;
    private static final Random RANDOM = new Random();

    public QuoteResponse getQuote(int id) {
        var quoteOpEnum = QuoteEnum.getById(id);
        if (quoteOpEnum.isPresent()) {
            var quotes = quoteOpEnum.get().getQuotes();
            var quote = quotes.get(RANDOM.nextInt(quotes.size()));
            var quoteData = new QuoteResponse(quote, id);
            log.debug("Quote data: {}", quoteData);
            return quoteData;
        }
        return null;
    }
}
