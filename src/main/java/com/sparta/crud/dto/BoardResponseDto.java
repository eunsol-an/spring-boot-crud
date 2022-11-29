package com.sparta.crud.dto;

import com.sparta.crud.entity.Board;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String name;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean success;

    public BoardResponseDto(Board board, Boolean success) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.name = board.getName();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.success = success;
    }

    public BoardResponseDto(Boolean success) {
        this.success = success;
    }

}