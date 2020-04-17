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

    public Card() {
    }

    public Card(String title, String description, CardList list) {

        this.list = list;
        this.title = title;
        this.description = description;
    }

    public Card(Long id, String title, String description, CardList list) {

        this.id = id;
        this.list = list;
        this.title = title;
        this.description = description;
    }
}
