package com.paw.trello.service;

import com.paw.trello.dao.CardRepository;
import com.paw.trello.dto.CardDto;
import com.paw.trello.entity.Card;
import com.paw.trello.exceptions.TableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        super();
        this.cardRepository = cardRepository;
    }

    public CardDto findById(Long id) throws TableNotFoundException {
        Card card = cardRepository.findById(id).orElseThrow(() -> new TableNotFoundException("Brak tabeli " + id));
        return mapFromTableListToDto(card);
    }

    public Iterable<CardDto> findAll() {
        List<Card> cardList = cardRepository.findAll();
        return cardList.stream().map(this::mapFromTableListToDto).collect(toList());
    }

    public Set<CardDto> findAllCardsFromList(Long id) {
        Set<Card> cardList = cardRepository.findAllByList_Id(id);
        return cardList.stream().map(this::mapFromTableListToDto).collect(toSet());
    }

    public Set<CardDto> findAllCardsByTable(Long id) {
        Set<Card> cardList = cardRepository.findAllByList_Ttable_Id(id);
        return cardList.stream().map(this::mapFromTableListToDto).collect(toSet());
    }

    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }

    public CardDto mapFromTableListToDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setTitle(card.getTitle());
        cardDto.setDescription(card.getDescription());
        cardDto.setList(CardListService.mapFromCardListToDto(card.getList()));
        return cardDto;
    }
}
