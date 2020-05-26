package com.paw.trello.service;

import com.paw.trello.dao.CardRepository;
import com.paw.trello.dao.FileRepository;
import com.paw.trello.dto.FileDto;
import com.paw.trello.entity.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final CardRepository cardRepository;

    @Autowired
    public FileService(FileRepository fileRepository, CardRepository cardRepository) {
        super();
        this.fileRepository = fileRepository;
        this.cardRepository = cardRepository;
    }

    public List <FileDto> getFiles(Long id) {
        List<FileModel> files = fileRepository.findAllByCard_Id(id);
        return files.stream().map(this::mapFromFileToDto).collect(Collectors.toList());
    }

    public String uploadMultipartFile(MultipartFile file, String cardId) {

        try {
            FileModel filemodel = new FileModel(file.getOriginalFilename(), file.getContentType(),
                    file.getBytes(), cardRepository.getOne(Long.parseLong(cardId)));
            fileRepository.save(filemodel);
            return "File uploaded successfully! Filename " + file.getOriginalFilename() + " card " + cardId;
        } catch (Exception e) {
            return "FAIL!";
        }
    }

    public void deleteById(Long id) {
        fileRepository.deleteById(id);
    }

    public FileDto mapFromFileToDto(FileModel fileModel) {
        FileDto fileDto = new FileDto();
        fileDto.setId(fileModel.getId());
        fileDto.setName(fileModel.getName());
        fileDto.setMimetype(fileModel.getMimetype());
        fileDto.setPic(fileModel.getPic());
        fileDto.setCard(CardService.mapFromTableListToDto(fileModel.getCard()));
        return fileDto;
    }
}
