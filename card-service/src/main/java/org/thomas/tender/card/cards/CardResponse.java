package org.thomas.tender.card.cards;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CardResponse(UUID id, BigDecimal balance, String currency, Instant createdAt) {

    public static CardResponse from(Card card) {
        return new CardResponse(card.getId(), card.getBalance(), card.getCurrency(), card.getCreatedAt());
    }
}
