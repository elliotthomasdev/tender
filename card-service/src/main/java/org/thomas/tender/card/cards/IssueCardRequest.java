package org.thomas.tender.card.cards;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record IssueCardRequest(@NotNull @Positive @Digits(integer = 10, fraction = 2) BigDecimal initialBalance,
                               @NotNull @Pattern(regexp = "[A-Z]{3}") String currency) {
}
