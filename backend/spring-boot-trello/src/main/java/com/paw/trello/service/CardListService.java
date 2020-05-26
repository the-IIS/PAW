package com.paw.trello.service;

import com.paw.trello.dao.CardListRepository;
import com.paw.trello.dto.CardListDto;
import com.paw.trello.dto.TableListDto;
import com.paw.trello.entity.Card;
import com.paw.trello.entity.CardList;
import com.paw.trello.entity.TableList;
import com.paw.trello.exceptions.TableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import  java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class CardListService {

    private final CardListRepository cardListRepository;

    @Autowired
    public CardListService(CardListRepository cardListRepository) {
        super();
        this.cardListRepository = cardListRepository;
    }

    public CardListDto findById(Long id) throws TableNotFoundException {
        CardList cardList = cardListRepository.findById(id).orElseThrow(() -> new TableNotFoundException("Brak tabeli " + id));
        return mapFromCardListToDto(cardList);
    }

    public List<CardListDto> findAll() {
        List<CardList> cardLists = cardListRepository.findAll();
        return cardLists.stream().map(CardListService::mapFromCardListToDto).collect(toList());
    }

    public Set<CardListDto> findAllCardListsFromTable(Long id) {
        Set<CardList> cardLists = cardListRepository.findAllByTtable_Id(id);
        return cardLists.stream().map(CardListService::mapFromCardListToDto).collect(toSet());
    }

    public CardList save(CardList cardList) {
        return cardListRepository.save(cardList);
    }

    public void deleteById(Long id) {
        cardListRepository.deleteById(id);
    }
    public static CardListDto mapFromCardListToDto(CardList cardList) {
        CardListDto cardListDto = new CardListDto();
        cardListDto.setId(cardList.getId());
        cardListDto.setListName(cardList.getListName());
        return cardListDto;
    }
}
