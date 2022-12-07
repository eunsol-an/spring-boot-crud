package com.sparta.crud.dto;

import com.sparta.crud.entity.Comment;
import lombok.Getter;

@Getter
public class CommentOneResponseDto extends BaseResponse {
    CommentToDto commentOne;
    public CommentOneResponseDto(boolean success, int statusCode, Comment comment) {
        super(success, statusCode);
        this.commentOne = new CommentToDto(comment);
    }
}
