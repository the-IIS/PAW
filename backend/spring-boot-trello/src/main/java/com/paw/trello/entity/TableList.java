package com.paw.trello.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="table_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    //@Getter(AccessLevel.NONE)     // jak NIE chcesz widzieć kaskadowo, to odkomentuj
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ttable")
    private Set<CardList> ttables;

    public TableList(Long id, String tableName) {

        this.id = id;
        this.tableName = tableName;
    }
}
