package org.thomas.tender.card.cards;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
class CardRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:18");

    private final CardRepository cardRepository;
    private final EntityManager entityManager;

    @Autowired
    CardRepositoryTest(CardRepository cardRepository, EntityManager entityManager) {
        this.cardRepository = cardRepository;
        this.entityManager = entityManager;
    }

    @Test
    void savesAndReloadsCard() {
        var saved = cardRepository.saveAndFlush(new Card(new BigDecimal("25.00"), "EUR"));
        entityManager.clear();
        var found = cardRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getBalance()).isEqualByComparingTo("25.00");
        assertThat(found.getCurrency()).isEqualTo("EUR");
        assertThat(found.getId()).isEqualTo(saved.getId());
        assertThat(found.getCreatedAt()).isNotNull();
        assertThat(found.getUpdatedAt()).isNotNull();
    }
}
