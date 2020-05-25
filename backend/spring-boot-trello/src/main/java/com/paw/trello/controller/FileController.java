package com.paw.trello.controller;

import com.paw.trello.dao.CardRepository;
import com.paw.trello.dao.FileRepository;
import com.paw.trello.entity.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    CardRepository cardRepository;

    @GetMapping("/all")
    public List<FileModel> getListFiles() {
        return fileRepository.findAll();
    }

    @PostMapping("/upload")
    public String uploadMultipartFile(@RequestParam("file") MultipartFile file,
                                      @RequestParam("cardId") String cardId) {
        try {
            FileModel filemodel = new FileModel(file.getOriginalFilename(), file.getContentType(),
                    file.getBytes(), cardRepository.getOne(Long.parseLong(cardId)));
            fileRepository.save(filemodel);
            return "File uploaded successfully! Filename " + file.getOriginalFilename() + " card " + cardId;
        } catch (  Exception e) {
            return "FAIL!";
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFile(@RequestParam(name = "id") Long fileId) {
        fileRepository.deleteById(fileId);
        return new ResponseEntity<>("File with ID:" + fileId + " deleted successfully", HttpStatus.OK);
    }
}
