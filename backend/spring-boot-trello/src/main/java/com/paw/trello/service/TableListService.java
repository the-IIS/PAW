package com.paw.trello.service;

import com.paw.trello.dao.TableListRepository;
import com.paw.trello.dao.UserRepository;
import com.paw.trello.dto.TableListDto;
import com.paw.trello.entity.TableList;
import com.paw.trello.exceptions.TableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class TableListService {

    private final TableListRepository tableListRepository;
    private final UserRepository userRepository;

    @Autowired
    public TableListService(TableListRepository tableListRepository, UserRepository userRepository) {
        super();
        this.tableListRepository = tableListRepository;
        this.userRepository = userRepository;
    }

    public TableListDto findById(Long id) throws TableNotFoundException {
        TableList tableList = tableListRepository.findById(id).orElseThrow(() -> new TableNotFoundException("Brak tabeli " + id));
        return mapFromTableListToDto(tableList);
    }

    public Iterable<TableListDto> findAll(Authentication auth) {
        Set<TableList> tableLists = tableListRepository.getAllByUserId(userRepository.findByUsername(auth.getName()).getId());
        return tableLists.stream().map(this::mapFromTableListToDto).collect(toList());
    }

    public TableList save(TableList tableList) {
        return tableListRepository.save(tableList);
    }

    public void deleteById(Long id) {
        tableListRepository.deleteById(id);
    }

    public TableListDto mapFromTableListToDto(TableList tableList) {
        TableListDto tableListDto = new TableListDto();
        tableListDto.setId(tableList.getId());
        tableListDto.setTableName(tableList.getTableName());
        tableListDto.setUser(tableList.getUser().getUsername());
        return tableListDto;
    }
}
