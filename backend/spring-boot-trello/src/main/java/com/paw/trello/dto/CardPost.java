package com.paw.trello.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardPost {
    private String cardName;
    private String description;
    private Long cardListId;
}
