package com.paw.trello.controller;

import com.paw.trello.dto.CardListDto;
import com.paw.trello.entity.CardList;
import com.paw.trello.exceptions.TableNotFoundException;
import com.paw.trello.service.CardListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/card-list")
public class CardListController {

    private CardListService cardListService;

    @Autowired
    public CardListController(CardListService cardListService) {
        this.cardListService = cardListService;
    }

    @GetMapping("/all")
    public Iterable<CardListDto> getAll() {
        return cardListService.findAll();
    }

    @GetMapping("/get/{id}")
    public CardListDto getById(@PathVariable @RequestBody Long id) throws TableNotFoundException {
        return cardListService.findById(id);
    }

    @GetMapping("/get/table/{id}")
    public Set<CardListDto> findAllCardsFromTable(@PathVariable @RequestBody Long id) {
        return cardListService.findAllCardListsFromTable(id);
    }

    @PostMapping
    public ResponseEntity<CardList> addCardList(@RequestBody CardList cardList) {
        CardList list = cardListService.save(cardList);
        return  new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCardList(@RequestParam(name = "listId") Long listId) {
        cardListService.deleteById(listId);
        return new ResponseEntity<>("List with ID:" + listId + " deleted successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CardList> updateCardList(@RequestBody CardList cardList) {
        CardList list = cardListService.save(cardList);
        return  new ResponseEntity<>(list, HttpStatus.OK);
    }
}

