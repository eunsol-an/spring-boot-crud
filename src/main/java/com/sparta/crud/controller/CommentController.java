package com.sparta.crud.controller;

import com.sparta.crud.dto.BaseResponse;
import com.sparta.crud.dto.CommentRequestDto;
import com.sparta.crud.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/{id}")
    public ResponseEntity<BaseResponse> addComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDto commentRequestDto,
            HttpServletRequest request) {
        return ResponseEntity.ok().body(commentService.addComment(id, commentRequestDto, request));
    }

    // 댓글 수정
    @PutMapping("/{boardId}/{cmtId}")
    public ResponseEntity<BaseResponse> updateComment(
            @PathVariable Long boardId,
            @PathVariable Long cmtId,
            @RequestBody CommentRequestDto commentRequestDto,
            HttpServletRequest request) {
        return ResponseEntity.ok().body(commentService.updateComment(boardId, cmtId, commentRequestDto, request));
    }

    // 댓글 삭제
    @DeleteMapping("/{boardId}/{cmtId}")
    public ResponseEntity<BaseResponse> deleteComment(
            @PathVariable Long boardId,
            @PathVariable Long cmtId,
            HttpServletRequest request) {
        return ResponseEntity.ok().body(commentService.deleteComment(boardId, cmtId, request));
    }
}
