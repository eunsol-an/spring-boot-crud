package com.sparta.crud.service;

import com.sparta.crud.dto.*;
import com.sparta.crud.entity.Board;
import com.sparta.crud.entity.Comment;
import com.sparta.crud.entity.User;
import com.sparta.crud.entity.UserRoleEnum;
import com.sparta.crud.repository.BoardRepository;
import com.sparta.crud.util.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.sparta.crud.util.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BaseResponse createBoard(BoardRequestDto requestDto, User user) {

        // 요청받은 DTO로 DB에 저장할 객체 만들기
        Board board = boardRepository.saveAndFlush(new Board(requestDto, user));
        return new BoardOneResponseDto(StatusEnum.OK, board);
    }

    @Transactional(readOnly = true)
    public BaseResponse getBoardList() {
        BoardListResponseDto boardListResponseDto = new BoardListResponseDto(StatusEnum.OK);
        List<Board> boardList = boardRepository.findAllByOrderByCreatedAtDesc();
        for (Board board : boardList) {
            List<CommentToDto> commentList = new ArrayList<>();
            for (Comment comment : board.getComments()) {
                commentList.add(new CommentToDto(comment));
            }
            boardListResponseDto.addBoard(new BoardToDto(board, commentList));
        }
        return boardListResponseDto;
    }

    @Transactional(readOnly = true)
    public BaseResponse getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new CustomException(NOT_FOUND_BOARD)
        );
        List<CommentToDto> commentList = new ArrayList<>();
        for (Comment comment : board.getComments()) {
            commentList.add(new CommentToDto(comment));
        }
        return new BoardOneResponseDto(StatusEnum.OK, board, commentList);
    }

    @Transactional
    public BaseResponse update(Long id, BoardRequestDto requestDto, User user) {
        // 영속성 Entity 조회
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new CutomException(NOT_FOUND_BOARD)
//        );

        // 사용자 권한 가져와서 ADMIN 이면 무조건 수정 가능, USER 면 본인이 작성한 글일 때만 수정 가능
        UserRoleEnum userRoleEnum = user.getRole();

        Board board;

        if (userRoleEnum == UserRoleEnum.ADMIN) {
            // 입력 받은 게시글 id와 일치하는 DB 조회
            board = boardRepository.findById(id).orElseThrow(
                    () -> new CustomException(NOT_FOUND_BOARD)
            );

        } else {
            // 입력 받은 게시글 id, 토큰에서 가져온 userId와 일치하는 DB 조회
            board = boardRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new CustomException(AUTHORIZATION)
            );
        }

        board.update(requestDto);

        List<CommentToDto> commentList = new ArrayList<>();
        for (Comment comment : board.getComments()) {
            commentList.add(new CommentToDto(comment));
        }

        return new BoardOneResponseDto(StatusEnum.OK, board, commentList);

    }

    @Transactional
    public BaseResponse deleteBoard(Long id, User user) {

        // 사용자 권한 가져와서 ADMIN 이면 무조건 삭제 가능, USER 면 본인이 작성한 글일 때만 삭제 가능
        UserRoleEnum userRoleEnum = user.getRole();

        Board board;

        if (userRoleEnum == UserRoleEnum.ADMIN) {
            // 입력 받은 게시글 id와 일치하는 DB 조회
            board = boardRepository.findById(id).orElseThrow(
                    () -> new CustomException(NOT_FOUND_BOARD)
            );

        } else {
            // 입력 받은 게시글 id, 토큰에서 가져온 userId와 일치하는 DB 조회
            board = boardRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new CustomException(AUTHORIZATION)
            );
        }

        boardRepository.deleteById(id);

        return new BaseResponse(StatusEnum.OK);
    }
}
