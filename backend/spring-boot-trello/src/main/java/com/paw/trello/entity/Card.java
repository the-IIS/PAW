package com.paw.trello.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(foreignKey = @ForeignKey(name = "list_id"), name = "list_id", nullable = false)
    private CardList list;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card")
    private Set<FileModel> files;

    public Card(Long id, String title, String description, CardList list) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.list = list;
    }
}