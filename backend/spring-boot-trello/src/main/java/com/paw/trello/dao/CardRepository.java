package com.paw.trello.dao;

import com.paw.trello.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface CardRepository extends JpaRepository<Card, Long> {
}
