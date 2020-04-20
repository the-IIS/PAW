package com.paw.trello.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="card_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "list_name")
    private String listName;

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "ttable_id", nullable = false)
    private TableList ttable;

    //@Getter(AccessLevel.NONE)     // jak NIE chcesz widzieć kaskadowo, to odkomentuj
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "list")
    private Set<Card> cards;

    public CardList(Long id, String listName, TableList ttable) {

        this.id = id;
        this.listName = listName;
        this.ttable = ttable;
    }
}
