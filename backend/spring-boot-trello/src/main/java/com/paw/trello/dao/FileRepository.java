package com.paw.trello.dao;


import com.paw.trello.entity.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface FileRepository extends JpaRepository<FileModel, Long> {
    List<FileModel> findAllByCard_Id(Long id);
}
