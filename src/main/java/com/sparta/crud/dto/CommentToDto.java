package com.sparta.crud.dto;

import com.sparta.crud.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentToDto {
    private Long id;
    private String username;
    private String comment;
    private int commentLikeCount;
    private boolean commentLikeCheck;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentToDto(Comment comment, boolean commentLikeCheck) {
        this.id = comment.getId();
        this.username = comment.getUsername();
        this.comment = comment.getComment();
        this.commentLikeCount = comment.getCommentLikeList().size();
        this.commentLikeCheck = commentLikeCheck;
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
