package com.paw.trello.controller;

import com.paw.trello.dto.FileDto;
import com.paw.trello.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public List<FileDto> getFiles(@RequestParam("cardId") String cardId) {
        return fileService.getFiles(Long.parseLong(cardId));
    }

    @PostMapping("/upload")
    public String uploadMultipartFile(@RequestParam("file") MultipartFile file,
                                      @RequestParam("cardId") String cardId) {
        return fileService.uploadMultipartFile(file, cardId);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFile(@RequestParam(name = "id") Long fileId) {
        fileService.deleteById(fileId);
        return new ResponseEntity<>("File with ID:" + fileId + " deleted successfully", HttpStatus.OK);
    }
}