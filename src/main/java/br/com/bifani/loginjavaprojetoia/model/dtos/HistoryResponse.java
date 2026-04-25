package br.com.bifani.loginjavaprojetoia.model.dtos;

import java.time.LocalDateTime;

public record HistoryResponse(
        Long id,
        String chat,
        LocalDateTime timestamp
) {
}

