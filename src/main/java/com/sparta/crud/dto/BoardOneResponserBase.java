package com.sparta.crud.dto;

import com.sparta.crud.entity.Board;
import lombok.Getter;

@Getter
public class BoardOneResponserBase extends BaseResponse {

    BoardToDto boardOne;

    public BoardOneResponserBase(Boolean success, int statusCode, Board board) {
        super(success, statusCode);
        this.boardOne = new BoardToDto(board);
    }
}
