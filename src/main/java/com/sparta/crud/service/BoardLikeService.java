//package com.sparta.crud.service;
//
//import com.sparta.crud.dto.BaseResponse;
//import com.sparta.crud.dto.StatusEnum;
//import com.sparta.crud.entity.Board;
//import com.sparta.crud.entity.BoardLike;
//import com.sparta.crud.entity.User;
//import com.sparta.crud.repository.BoardLikeRepository;
//import com.sparta.crud.repository.BoardRepository;
//import com.sparta.crud.util.exception.CustomException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//import static com.sparta.crud.util.exception.ErrorCode.NOT_FOUND_BOARD;
//
//@Service
//@RequiredArgsConstructor
//public class BoardLikeService {
//
//    private final BoardLikeRepository boardLikeRepository;
//    private final BoardRepository boardRepository;
//
//    @Transactional(readOnly = true)
//    public boolean checkBoradLike(Long boardId, User user) {
//        // 해당 회원의 좋아요 여부 확인
//        Optional<BoardLike> boardLike = boardLikeRepository.findByBoardIdAndUserId(boardId, user.getId());
//        return boardLike.isEmpty();
//    }
//
//    @Transactional
//    public BaseResponse saveBoradLike(Long boardId, User user) {
//        // 입력 받은 게시글 id와 일치하는 DB 조회
//        Board board = boardRepository.findById(boardId).orElseThrow(
//                () -> new CustomException(NOT_FOUND_BOARD)
//        );
//
//        // 해당 회원의 좋아요 여부를 확인하고 비어있으면 좋아요, 아니면 좋아요 취소
//        if (checkBoradLike(boardId, user)) {
//            boardLikeRepository.saveAndFlush(new BoardLike(board, user));
//            return new BaseResponse(StatusEnum.PLUS_BOARD_LIKE);
//        } else {
//            boardLikeRepository.deleteByBoardIdAndUserId(boardId, user.getId());
//            return new BaseResponse(StatusEnum.MINUS_BOARD_LIKE);
//        }
//    }
//}
