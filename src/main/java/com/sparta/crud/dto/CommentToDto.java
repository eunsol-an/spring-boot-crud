package com.sparta.crud.dto;

import com.sparta.crud.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentToDto {
    private Long id;
    private String username;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentToDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.comment = comment.getComment();;
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
