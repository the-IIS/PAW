package com.paw.trello;

import com.paw.trello.dao.CardListRepository;
import com.paw.trello.dao.CardRepository;
import com.paw.trello.dao.TableListRepository;
import com.paw.trello.entity.Card;
import com.paw.trello.entity.CardList;
import com.paw.trello.entity.TableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RunAtStart {

    @Autowired
    private TableListRepository tableListRepository;

    @Autowired
    private CardListRepository cardListRepository;

    @Autowired
    private CardRepository cardRepository;

    @PostConstruct
    public void runAtStart() {

        TableList table1 = tableListRepository.save(new TableList("TABLE ONE"));

        CardList list1 = cardListRepository.save(new CardList("WALKING SKELETON", table1));

        Card card1 = cardRepository.save(new Card("THE CONNECTION", "IS WORKING", list1));
    }
}
