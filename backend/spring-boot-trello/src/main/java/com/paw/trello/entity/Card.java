package com.paw.trello.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
    private CardList list;

}