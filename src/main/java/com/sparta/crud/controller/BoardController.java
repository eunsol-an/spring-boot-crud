package com.sparta.crud.controller;

import com.sparta.crud.dto.*;
import com.sparta.crud.security.UserDetailsImpl;
import com.sparta.crud.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 작성
//    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping
    public ResponseEntity<BaseResponse> createBorad(
            @RequestBody BoardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(boardService.createBoard(requestDto, userDetails.getUser()));
    }

    // 전체 게시글 조회
    @GetMapping
    public ResponseEntity<BaseResponse> getBoardList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(boardService.getBoardList(userDetails.getUser()));
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getBoard(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(boardService.getBoard(id, userDetails.getUser()));
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(boardService.update(id, requestDto, userDetails.getUser()));
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteBoard(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(boardService.deleteBoard(id, userDetails.getUser()));
    }
}
