package com.sparta.crud.dto;

import com.sparta.crud.entity.Board;
import lombok.Getter;

@Getter
public class BoardOneResponserDto extends ResponseDto {

    BoardToDto boardOne;

    public BoardOneResponserDto(String success, int statusCode, Board board) {
        super(success, statusCode);
        this.boardOne = new BoardToDto(board);
    }
}
