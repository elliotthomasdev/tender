package org.thomas.tender.card.cards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    CardRepository cardRepository;

    @InjectMocks
    CardService cardService;

    @Test
    void issuesCardSuccessfully() {
        // Arrange
        IssueCardRequest request = new IssueCardRequest(new BigDecimal("25.00"), "EUR");
        when(cardRepository.saveAndFlush(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        CardResponse response = cardService.issue(request);

        // Assert
        assertThat(response.balance()).isEqualByComparingTo("25.00");
        assertThat(response.currency()).isEqualTo("EUR");

        // Verify
        verify(cardRepository).saveAndFlush(any(Card.class));
    }
}
