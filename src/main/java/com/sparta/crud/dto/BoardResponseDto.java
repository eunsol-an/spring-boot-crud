package com.sparta.crud.dto;

import com.sparta.crud.entity.Board;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponseDto{

    private BoardToDto boardOne;

    public BoardResponseDto(Board board) {
        this.boardOne = new BoardToDto(board);
    }

    public BoardResponseDto(Board board, List<CommentToDto> commentList) {
        this.boardOne = new BoardToDto(board, commentList);
    }
}
