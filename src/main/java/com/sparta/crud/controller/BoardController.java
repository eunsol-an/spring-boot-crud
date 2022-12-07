package com.sparta.crud.controller;

import com.sparta.crud.dto.*;
import com.sparta.crud.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping
    public BoardOneResponseDto createBorad(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return boardService.createBoard(requestDto, request);
    }

    // 전체 게시글 조회
    @GetMapping
    public BoardListResponseDto getBoardList() {
        return boardService.getBoardList();
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public BoardOneResponseDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public BaseResponse updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return boardService.update(id, requestDto, request);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public BaseResponse deleteBoard(@PathVariable Long id, HttpServletRequest request) {
        return boardService.deleteBoard(id, request);
    }
}
