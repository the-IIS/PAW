package com.paw.trello.dao;


import com.paw.trello.entity.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long> {
    List<FileModel> findAllByCard_Id(Long id);
    List<FileModel> findAllByCard_List_Id(Long id);
    List<FileModel> findAllByCard_List_Ttable_Id(Long id);
}
