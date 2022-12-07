package com.sparta.crud.service;

import com.sparta.crud.dto.*;
import com.sparta.crud.entity.Board;
import com.sparta.crud.entity.Comment;
import com.sparta.crud.entity.User;
import com.sparta.crud.entity.UserRoleEnum;
import com.sparta.crud.jwt.JwtUtil;
import com.sparta.crud.repository.BoardRepository;
import com.sparta.crud.repository.CommentRepository;
import com.sparta.crud.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public BaseResponse addComment(Long id, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        // 게시글의 DB 저장 유무 확인
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        // Request에서 토큰 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 댓글 작성 가능
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
            Comment comment = commentRepository.save(new Comment(commentRequestDto, user.getUsername(), board));

            return new CommentOneResponseDto(true, HttpStatus.OK.value(), comment);

        } else {
            return  null;
        }
    }

    @Transactional
    public BaseResponse updateComment(Long boardId, Long cmtId, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        // 게시글의 DB 저장 유무 확인
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        // Request에서 토큰 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 댓글 수정 가능
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

            // 사용자 권한 가져와서 ADMIN 이면 무조건 수정 가능, USER 면 본인이 작성한 댓글일 때만 수정 가능
            UserRoleEnum userRoleEnum = user.getRole();

            Comment comment;

            if (userRoleEnum == UserRoleEnum.ADMIN) {
                // 입력 받은 댓글 id와 일치하는 DB 조회
                comment = commentRepository.findById(cmtId).orElseThrow(
                        () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
                );
            } else {
                // 입력 받은 댓글 id, 토큰에서 가져온 username과 일치하는 DB 조회
                comment = commentRepository.findByIdAndUsername(cmtId, user.getUsername()).orElseThrow(
                        () -> new IllegalArgumentException("해당하는 댓글이 존재하지 않습니다.")
                );
            }

            // 요청 받은 DTO로 DB에 업데이트
            comment.update(commentRequestDto);

            return new CommentOneResponseDto(true, HttpStatus.OK.value(), comment);

        } else {
            return  null;
        }
    }

    @Transactional
    public BaseResponse deleteComment(Long boardId, Long cmtId, HttpServletRequest request) {
        // 게시글의 DB 저장 유무 확인
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        // Request에서 토큰 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 댓글 수정 가능
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

            // 사용자 권한 가져와서 ADMIN 이면 무조건 수정 가능, USER 면 본인이 작성한 댓글일 때만 수정 가능
            UserRoleEnum userRoleEnum = user.getRole();

            Comment comment;

            if (userRoleEnum == UserRoleEnum.ADMIN) {
                // 입력 받은 댓글 id와 일치하는 DB 조회
                comment = commentRepository.findById(cmtId).orElseThrow(
                        () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
                );
            } else {
                // 입력 받은 댓글 id, 토큰에서 가져온 username과 일치하는 DB 조회
                comment = commentRepository.findByIdAndUsername(cmtId, user.getUsername()).orElseThrow(
                        () -> new IllegalArgumentException("해당하는 댓글이 존재하지 않습니다.")
                );
            }

            // 해당 댓글 삭제
            commentRepository.deleteById(cmtId);

            return new BaseResponse(true, HttpStatus.OK.value());

        } else {
            return new BaseResponse(false, HttpStatus.NOT_FOUND.value());
        }
    }
}
