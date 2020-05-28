package com.paw.trello.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardUpdatePost {
    private String cardName;
    private String description;
    private Long cardId;
}
