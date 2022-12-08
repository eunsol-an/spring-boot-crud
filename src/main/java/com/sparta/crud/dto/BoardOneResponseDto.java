package com.sparta.crud.dto;

import com.sparta.crud.entity.Board;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardOneResponseDto extends BaseResponse {

    private BoardToDto boardOne;

    public BoardOneResponseDto(StatusEnum status, Board board) {
        super(status);
        this.boardOne = new BoardToDto(board);
    }

    public BoardOneResponseDto(StatusEnum status, Board board, List<CommentToDto> commentList) {
        super(status);
        this.boardOne = new BoardToDto(board, commentList);
    }
}
