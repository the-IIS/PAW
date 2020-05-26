package com.paw.trello.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileDto {

    private Long id;
    private String name;
    private String mimetype;
    private byte[] pic;
    private CardDto card;
}
