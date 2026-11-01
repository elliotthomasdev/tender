package org.thomas.tender.card.cards;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional
    public CardResponse issue(IssueCardRequest request) {
        Card saved = cardRepository.saveAndFlush(new Card(request.initialBalance(), request.currency()));
        return CardResponse.from(saved);
    }
}
