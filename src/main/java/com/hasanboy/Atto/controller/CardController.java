package com.hasanboy.Atto.controller;

import com.hasanboy.Atto.model.Card;
import com.hasanboy.Atto.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/create/card")
    public ResponseEntity<?> createCard(@RequestBody Card card) {
        Card result = cardService.create(card);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/create/balance")
    public ResponseEntity<?> addbalance(@RequestParam("n") String number,
                                        @RequestParam("c") BigDecimal cesh) {
        Card result = cardService.addBalance(number, cesh);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByCard/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        Card result = cardService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllCard")
    public ResponseEntity<?> getAllCard() {
        List<Card> result = cardService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/card/number/{number}")
    public ResponseEntity<?> getCardNumber(@PathVariable("number") String number) {
        Card result = cardService.getByCardNumber(number);
        return ResponseEntity.ok(result);
    }

}
