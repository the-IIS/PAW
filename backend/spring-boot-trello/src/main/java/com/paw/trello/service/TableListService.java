package com.paw.trello.service;

import com.paw.trello.dao.TableListRepository;
import com.paw.trello.entity.TableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TableListService {

    private final TableListRepository tableListRepository;

    @Autowired
    public TableListService(TableListRepository tableListRepository) {
        super();
        this.tableListRepository = tableListRepository;
    }

    public Optional<TableList> findById(Long id) {
        return tableListRepository.findById(id);
    }

    public Iterable<TableList> findAll() {
        return tableListRepository.findAll();
    }

    public TableList save(TableList tableList) {
        return tableListRepository.save(tableList);
    }

    public void deleteById(Long id) {
        tableListRepository.deleteById(id);
    }
}
