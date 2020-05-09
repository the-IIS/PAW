package com.paw.trello.dao;

import com.paw.trello.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Set;

@CrossOrigin("http://localhost:4200")
public interface CardRepository extends JpaRepository<Card, Long> {
    Set<Card> findAllByList_Id(Long id);
    Set<Card> findAllByList_Ttable_Id(Long id);
}
