package kanban_board.controller;

import kanban_board.models.Card;
import kanban_board.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;
    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }
    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card createdCard = cardService.createCard(card);
        return ResponseEntity.ok(createdCard);
    }
    @GetMapping("/{cardId}")
    public ResponseEntity<Card> getCardById(@PathVariable long cardId) {
        Card card = cardService.getCardById(cardId);
        return ResponseEntity.ok(card);
    }
    @PutMapping("/{cardId}")
    public ResponseEntity<Card> updateCard(@PathVariable long cardId, @RequestBody Card updatedCard) {
        Card card = cardService.updateCard(cardId, updatedCard);
        return ResponseEntity.ok(card);
    }
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable long cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.noContent().build();
    }
}
