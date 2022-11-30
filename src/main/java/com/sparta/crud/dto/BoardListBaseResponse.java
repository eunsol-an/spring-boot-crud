package com.sparta.crud.dto;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardListBaseResponse extends BaseResponse {

    List<BoardToDto> boradList = new ArrayList<>();

    public BoardListBaseResponse(Boolean success, int statusCode) {
        super(success, statusCode);
    }

    public void addBoard(BoardToDto boardToDto) {
        boradList.add(boardToDto);
    }
}
