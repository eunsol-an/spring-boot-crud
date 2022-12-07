package com.sparta.crud.controller;

import com.sparta.crud.dto.BaseResponse;
import com.sparta.crud.dto.CommentRequestDto;
import com.sparta.crud.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/{id}")
    public BaseResponse addComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDto commentRequestDto,
            HttpServletRequest request) {
        return commentService.addComment(id, commentRequestDto, request);
    }

    // 댓글 수정
    @PutMapping("/{boardId}/{cmtId}")
    public BaseResponse updateComment(
            @PathVariable Long boardId,
            @PathVariable Long cmtId,
            @RequestBody CommentRequestDto commentRequestDto,
            HttpServletRequest request) {
        return commentService.updateComment(boardId, cmtId, commentRequestDto, request);
    }

    // 댓글 삭제
    @DeleteMapping("/{boardId}/{cmtId}")
    public BaseResponse deleteComment(
            @PathVariable Long boardId,
            @PathVariable Long cmtId,
            HttpServletRequest request) {
        return commentService.deleteComment(boardId, cmtId, request);
    }
}
