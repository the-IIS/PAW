package com.paw.trello.service;

import com.paw.trello.dao.CardListRepository;
import com.paw.trello.entity.CardList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import  java.util.List;

@Service
public class CardListService {

    private final CardListRepository cardListRepository;

    @Autowired
    public CardListService(CardListRepository cardListRepository) {
        super();
        this.cardListRepository = cardListRepository;
    }

    public Optional<CardList> findById(Long id) {
        return cardListRepository.findById(id);
    }

    public List<CardList> findAll() {
        return cardListRepository.findAll();
    }

    public CardList save(CardList cardList) {
        return cardListRepository.save(cardList);
    }

    public void deleteById(Long id) {
        cardListRepository.deleteById(id);
    }
}
