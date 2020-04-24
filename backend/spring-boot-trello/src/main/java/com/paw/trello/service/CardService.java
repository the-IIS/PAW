package com.paw.trello.service;

import com.paw.trello.dao.CardRepository;
import com.paw.trello.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    private CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        super();
        this.cardRepository = cardRepository;
    }

    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    public Iterable<Card> findAll() {
        return cardRepository.findAll();
    }

    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }
}
