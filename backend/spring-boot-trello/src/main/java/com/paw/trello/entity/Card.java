package com.paw.trello.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="card")
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private CardList list;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
