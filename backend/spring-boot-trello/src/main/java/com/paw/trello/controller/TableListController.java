package com.paw.trello.controller;

import com.paw.trello.entity.TableList;
import com.paw.trello.service.TableListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/table-list")
public class TableListController {

    private TableListService tableListService;

    @Autowired
    public TableListController(TableListService tableListService) {
        this.tableListService = tableListService;
    }

    @GetMapping("/all")
    public Iterable<TableList> getAll() {
        return tableListService.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<TableList> getById(@PathVariable @RequestBody Long id) {
        return tableListService.findById(id);
    }

    @PostMapping
    public ResponseEntity<TableList> addTableList(@RequestBody TableList tableList) {
        TableList table = tableListService.save(tableList);
        return  new ResponseEntity<>(table, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTableList(@RequestParam(name = "tableId") Long tableId) {
        tableListService.deleteById(tableId);
        return new ResponseEntity<>("Table with ID:" + tableId + " deleted successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TableList> updateTableList(@RequestBody TableList tableList) {
        TableList table = tableListService.save(tableList);
        return  new ResponseEntity<>(table, HttpStatus.OK);
    }
}
