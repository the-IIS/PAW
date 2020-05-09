package com.paw.trello.dao;

import com.paw.trello.entity.CardList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Set;

@CrossOrigin("http://localhost:4200")
public interface CardListRepository extends JpaRepository<CardList, Long> {
    Set<CardList> findAllByTtable_Id(Long id);
}
