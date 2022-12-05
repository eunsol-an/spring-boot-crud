package com.sparta.crud.dto;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardListResponseDto extends BaseResponse {

    List<BoardToDto> boradList = new ArrayList<>();

    public BoardListResponseDto(Boolean success, int statusCode) {
        super(success, statusCode);
    }

    public void addBoard(BoardToDto boardToDto) {
        boradList.add(boardToDto);
    }
}
