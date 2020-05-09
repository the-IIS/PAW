package com.paw.trello.controller;

import com.paw.trello.entity.Card;
import com.paw.trello.entity.CardList;
import com.paw.trello.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/card")
public class CardController {

    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/all")
    public Iterable<Card> getAll() {
        return cardService.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<Card> getById(@PathVariable @RequestBody Long id) {
        return cardService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Card> addCard(@RequestBody Card card) {
        Card cardd = cardService.save(card);
        return  new ResponseEntity<>(cardd, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCard(@RequestParam(name = "cardId") Long cardId) {
        cardService.deleteById(cardId);
        return new ResponseEntity<>("Card with ID:" + cardId + " deleted successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Card> updateCard(@RequestBody Card card) {
        Card cardd = cardService.save(card);
        return  new ResponseEntity<>(cardd, HttpStatus.OK);
    }
}
