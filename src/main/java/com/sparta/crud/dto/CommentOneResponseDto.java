package com.sparta.crud.dto;

import com.sparta.crud.entity.Comment;
import lombok.Getter;

@Getter
public class CommentOneResponseDto extends BaseResponse {
    CommentToDto commentOne;
    public CommentOneResponseDto(StatusEnum status, Comment comment, boolean commentLikeCheck) {
        super(status);
        this.commentOne = new CommentToDto(comment, commentLikeCheck);
    }
}
