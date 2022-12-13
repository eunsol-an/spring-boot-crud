//package com.sparta.crud.controller;
//
//import com.sparta.crud.dto.BaseResponse;
//import com.sparta.crud.security.UserDetailsImpl;
//import com.sparta.crud.service.BoardLikeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/board/like")
//@RequiredArgsConstructor
//public class BoardLikeController {
//
//    private final BoardLikeService boardLikeService;
//
//    @PostMapping("/{boardId}")
//    public ResponseEntity<BaseResponse> saveBoardLike(
//            @PathVariable Long boardId,
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return ResponseEntity.ok().body(boardService.saveBoardLike(boardId, userDetails.getUser()));
//    }
//}
