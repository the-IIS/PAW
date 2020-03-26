package com.paw.trello.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="card_list")
// @Data -- known bug for one to many
@Getter
@Setter
public class CardList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "list_name")
    private String listName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "list")
    private Set<Card> cards;
}
