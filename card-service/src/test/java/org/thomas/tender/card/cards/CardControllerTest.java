package org.thomas.tender.card.cards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.hamcrest.Matchers.endsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CardController.class)
class CardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CardService cardService;

    @Test
    void postCards_ValidRequest_Returns201WithCard() throws Exception {
        UUID id = UUID.randomUUID();
        Instant createdAt = Instant.now();

        when(cardService.issue(any())).thenReturn(new CardResponse(id, new BigDecimal("25.00"), "USD", createdAt));

        mockMvc.perform(post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"initialBalance": 25.00, "currency": "USD"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith("/cards/" + id)))
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.balance").value(25.00));

        verify(cardService).issue(new IssueCardRequest(new BigDecimal("25.00"), "USD"));
    }

    @Test
    void postCards_InvalidBody_Returns400() throws Exception {
        mockMvc.perform(post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"initialBalance": 25.00, "currency": "usa"}
                                """))
                .andExpect(status().isBadRequest());

        verify(cardService, never()).issue(any());
    }
}
