package com.paw.trello.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="table_list")
// @Data -- known bug for one to many
@Getter
@Setter
public class TableList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ttable")
    private Set<CardList> ttables;

    public TableList() {
    }

    public TableList(String tableName) {

        this.tableName = tableName;
    }

    public TableList(Long id, String tableName) {

        this.id = id;
        this.tableName = tableName;
    }
}
