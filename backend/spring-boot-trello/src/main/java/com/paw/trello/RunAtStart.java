package com.paw.trello;

import com.paw.trello.dao.CardListRepository;
import com.paw.trello.dao.CardRepository;
import com.paw.trello.dao.TableListRepository;
import com.paw.trello.dao.UserRepository;
import com.paw.trello.entity.Card;
import com.paw.trello.entity.CardList;
import com.paw.trello.entity.TableList;
import com.paw.trello.entity.User;
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

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void runAtStart() {

        TableList table1 = tableListRepository.save(new TableList(1L, "TABLE ONE"));

        CardList list1 = cardListRepository.save(new CardList(1L, "WALKING SKELETON", table1));

        Card card1 = cardRepository.save(new Card(1L, "THE CONNECTION", "IS WORKING", list1));

        User userTemp = new User();
        userTemp.setUsername("root");
        userTemp.setPassword("$2a$10$ckaGf3PJJKBXtAw9/FQgceimEaAwRW9eplk2vovhk11j8bZJGip5q");
        User user = userRepository.save(userTemp);
    }
}
