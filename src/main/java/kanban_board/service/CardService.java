package kanban_board.service;

import javassist.NotFoundException;
import kanban_board.models.Card;
import kanban_board.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CardService {
    private final CardRepository cardRepository;
    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    public Card updateCard(long cardId, Card updatedCard) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found with id: " + cardId));

        card.setCardTitle(updatedCard.getCardTitle());
        card.setDescription(updatedCard.getDescription());

        updatedCard = cardRepository.save(card);
        return updatedCard;
    }
    public Card getCardById(long cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found with id: " + cardId));
    }
    public void deleteCard(long cardId) {
        cardRepository.deleteById(cardId);
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }
}
