package com.paw.trello.service;

import com.paw.trello.dao.CardListRepository;
import com.paw.trello.dao.TableListRepository;
import com.paw.trello.dto.CardListDto;
import com.paw.trello.dto.CardListPost;
import com.paw.trello.dto.CardPost;
import com.paw.trello.entity.Card;
import com.paw.trello.entity.CardList;
import com.paw.trello.exceptions.TableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
public class CardListService {

    private final CardListRepository cardListRepository;
    private final TableListRepository tableListRepository;

    @Autowired
    public CardListService(CardListRepository cardListRepository, TableListRepository tableListRepository) {
        super();
        this.cardListRepository = cardListRepository;
        this.tableListRepository = tableListRepository;
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

    public CardList add(CardListPost cardListPost) throws TableNotFoundException {
        CardList cardList = new CardList();
        cardList.setListName(cardListPost.getListName());
        cardList.setTtable(tableListRepository.findById(cardListPost.getTable_id()).orElseThrow(() -> new TableNotFoundException("Brak tabeli")));
        return cardListRepository.save(cardList);
    }

    public static CardListDto mapFromCardListToDto(CardList cardList) {
        CardListDto cardListDto = new CardListDto();
        cardListDto.setId(cardList.getId());
        cardListDto.setListName(cardList.getListName());
        cardListDto.setArchive(cardList.isArchive());
        return cardListDto;
    }

    public CardList archive(Long id) throws TableNotFoundException {
        CardList cardList = cardListRepository.findById(id).orElseThrow(() -> new TableNotFoundException("Brak listy"));
        cardList.setArchive(true);
        return cardListRepository.save(cardList);
    }

    public void update(CardListPost cardListPost, Long id) throws TableNotFoundException {
        CardList cardList = cardListRepository.findById(id).orElseThrow(() -> new TableNotFoundException("Brak listy "));
        cardList.setListName(cardListPost.getListName());
        cardListRepository.save(cardList);
    }
}
