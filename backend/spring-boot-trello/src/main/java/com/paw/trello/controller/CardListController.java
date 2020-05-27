package com.paw.trello.controller;

import com.paw.trello.dto.CardListDto;
import com.paw.trello.dto.CardListPost;
import com.paw.trello.dto.CardPost;
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

    @GetMapping("/{id}/archive")
    public ResponseEntity<String> archive(@PathVariable @RequestBody Long id) throws TableNotFoundException {
        cardListService.archive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/table/{id}")
    public Set<CardListDto> findAllCardsFromTable(@PathVariable @RequestBody Long id) {
        return cardListService.findAllCardListsFromTable(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCardList(@RequestBody CardListPost cardList) throws TableNotFoundException {
        cardListService.add(cardList);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCard(@PathVariable @RequestBody Long id, @RequestBody CardListPost cardListPost) throws TableNotFoundException {
        cardListService.update(cardListPost, id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}

