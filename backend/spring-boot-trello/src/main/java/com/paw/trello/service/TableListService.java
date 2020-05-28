package com.paw.trello.service;

import com.paw.trello.dao.TableListRepository;
import com.paw.trello.dao.UserRepository;
import com.paw.trello.dto.TableListDto;
import com.paw.trello.entity.TableList;
import com.paw.trello.exceptions.TableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        tableListDto.setPicName(tableList.getPicName());
        tableListDto.setMimetype(tableList.getMimetype());
        tableListDto.setPic(tableList.getPic());
        return tableListDto;
    }

    public void updateById(Long id, String name) throws TableNotFoundException {
        TableList tableList = tableListRepository.findById(id).orElseThrow(() -> new TableNotFoundException("Brak tabeli " + id));
        tableList.setTableName(name);
        tableListRepository.save(tableList);
    }

    public String uploadBackgroundPicture(MultipartFile file, String tableId) throws TableNotFoundException {

        try {
            TableList tableList = tableListRepository.findById(Long.parseLong(tableId))
                    .orElseThrow(() -> new TableNotFoundException("Brak tabeli " + tableId));
            tableList.setPicName(file.getOriginalFilename());
            tableList.setMimetype(file.getContentType());
            tableList.setPic(file.getBytes());
            tableListRepository.save(tableList);

            return "File uploaded successfully! Filename " + file.getOriginalFilename() + " table " + tableId;

        } catch (Exception e) {
            return "FAIL!";
        }
    }

    public void deleteBackgroundPicture(Long tableId) throws TableNotFoundException{

        TableList tableList = tableListRepository.findById(tableId)
                .orElseThrow(() -> new TableNotFoundException("Brak tabeli " + tableId));
        tableList.setPicName(null);
        tableList.setMimetype(null);
        tableList.setPic(null);
        tableListRepository.save(tableList);
    }
}
