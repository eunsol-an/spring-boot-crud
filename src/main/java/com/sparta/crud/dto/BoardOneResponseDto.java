package com.sparta.crud.dto;

import com.sparta.crud.entity.Board;
import lombok.Getter;

@Getter
public class BoardOneResponseDto extends BaseResponse {

    BoardToDto boardOne;

    public BoardOneResponseDto(Boolean success, int statusCode, Board board) {
        super(success, statusCode);
        this.boardOne = new BoardToDto(board);
    }
}
