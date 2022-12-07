package com.sparta.crud.service;

import com.sparta.crud.dto.*;
import com.sparta.crud.entity.Board;
import com.sparta.crud.entity.Comment;
import com.sparta.crud.entity.User;
import com.sparta.crud.entity.UserRoleEnum;
import com.sparta.crud.jwt.JwtUtil;
import com.sparta.crud.repository.BoardRepository;
import com.sparta.crud.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public BoardOneResponseDto createBoard(BoardRequestDto requestDto, HttpServletRequest request) {
        // Request에서 토큰 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시글 작성 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청 받은 DTO로 DB에 저장할 객체 만들기
            Board board = boardRepository.saveAndFlush(new Board(requestDto, user.getUsername()));

            return new BoardOneResponseDto(true, HttpStatus.OK.value(), board);

        } else {
            return  null;
        }

    }

    @Transactional(readOnly = true)
    public BoardListResponseDto getBoardList() {
        BoardListResponseDto boardListResponseDto = new BoardListResponseDto(true, HttpStatus.OK.value());
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
    public BoardOneResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        List<CommentToDto> commentList = new ArrayList<>();
        for (Comment comment : board.getComments()) {
            commentList.add(new CommentToDto(comment));
        }
        return new BoardOneResponseDto(true, HttpStatus.OK.value(), board, commentList);
    }

    @Transactional
    public BaseResponse update(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
        // 영속성 Entity 조회
//        Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
//        );

        // Request에서 토큰 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시글 수정 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 사용자 권한 가져와서 ADMIN 이면 무조건 수정 가능, USER 면 본인이 작성한 글일 때만 수정 가능
            UserRoleEnum userRoleEnum = user.getRole();

            Board board;

            if (userRoleEnum == UserRoleEnum.ADMIN) {
                // 입력 받은 게시글 id와 일치하는 DB 조회
                board = boardRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
                );

            } else {
                // 입력 받은 게시글 id, 토큰에서 가져온 username과 일치하는 DB 조회
                board = boardRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(
                        () -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다.")
                );
            }

            board.update(requestDto);

            List<CommentToDto> commentList = new ArrayList<>();
            for (Comment comment : board.getComments()) {
                commentList.add(new CommentToDto(comment));
            }

            return new BoardOneResponseDto(true, HttpStatus.OK.value(), board, commentList);

        } else {
            return new BaseResponse(false, HttpStatus.NOT_FOUND.value());
        }

    }

    @Transactional
    public BaseResponse deleteBoard(Long id, HttpServletRequest request) {
        // Request에서 토큰 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시글 수정 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 사용자 권한 가져와서 ADMIN 이면 무조건 삭제 가능, USER 면 본인이 작성한 글일 때만 삭제 가능
            UserRoleEnum userRoleEnum = user.getRole();

            Board board;

            if (userRoleEnum == UserRoleEnum.ADMIN) {
                // 입력 받은 게시글 id와 일치하는 DB 조회
                board = boardRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
                );

            } else {
                // 입력 받은 게시글 id, 토큰에서 가져온 username과 일치하는 DB 조회
                board = boardRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(
                        () -> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다.")
                );
            }

            boardRepository.deleteById(id);

            return new BaseResponse(true, HttpStatus.OK.value());

        } else {
            return new BaseResponse(false, HttpStatus.NOT_FOUND.value());
        }
    }
}
