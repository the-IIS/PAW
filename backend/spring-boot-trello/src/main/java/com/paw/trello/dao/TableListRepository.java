package com.paw.trello.dao;

import com.paw.trello.entity.TableList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface TableListRepository extends JpaRepository<TableList, Long> {
}
