package com.sparta.crud.dto;

import com.sparta.crud.entity.Board;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardOneResponseDto extends BaseResponse {

    private BoardToDto boardOne;

    public BoardOneResponseDto(Boolean success, int statusCode, Board board) {
        super(success, statusCode);
        this.boardOne = new BoardToDto(board);
    }

    public BoardOneResponseDto(Boolean success, int statusCode, Board board, List<CommentToDto> commentList) {
        super(success, statusCode);
        this.boardOne = new BoardToDto(board, commentList);
    }
}
