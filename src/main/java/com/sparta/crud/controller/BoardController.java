package com.sparta.crud.controller;

import com.sparta.crud.dto.*;
import com.sparta.crud.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping("/board")
    public BoardOneResponserDto createBorad(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    // 전체 게시글 조회
    @GetMapping("/board")
    public BoardListResponseDto getBoardList() {
        return boardService.getBoardList();
    }

    // 게시글 상세 조회
    @GetMapping("/board/{id}")
    public BoardOneResponserDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    // 게시글 수정
    @PutMapping("/board/{id}")
    public ResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/board/{id}")
    public ResponseDto deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteBoard(id, requestDto);
    }
}
