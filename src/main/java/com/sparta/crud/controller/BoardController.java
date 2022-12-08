package com.sparta.crud.controller;

import com.sparta.crud.dto.*;
import com.sparta.crud.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<BaseResponse> createBorad(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(boardService.createBoard(requestDto, request));
    }

    // 전체 게시글 조회
    @GetMapping
    public ResponseEntity<BaseResponse> getBoardList() {
        return ResponseEntity.ok().body(boardService.getBoardList());
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok().body(boardService.getBoard(id));
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return ResponseEntity.ok().body(boardService.update(id, requestDto, request));
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteBoard(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok().body(boardService.deleteBoard(id, request));
    }
}
