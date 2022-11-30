package com.sparta.crud.service;

import com.sparta.crud.dto.*;
import com.sparta.crud.entity.Board;
import com.sparta.crud.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardOneResponserDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardOneResponserDto("true", HttpStatus.OK.value(), board);
    }

    @Transactional(readOnly = true)
    public BoardListResponseDto getBoardList() {
        BoardListResponseDto boardListResponseDto = new BoardListResponseDto("true", HttpStatus.OK.value());
        List<Board> boardList = boardRepository.findAllByOrderByCreatedAtDesc();
        for (Board board : boardList) {
            boardListResponseDto.addBoard(new BoardToDto(board));
        }
        return boardListResponseDto;
    }

    @Transactional(readOnly = true)
    public BoardOneResponserDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new BoardOneResponserDto("ture", HttpStatus.OK.value(), board);
    }

//    @Transactional
    public ResponseDto update(Long id, BoardRequestDto requestDto) {
        // 영속성 Entity 조회
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        // 비밀번호 일치
        if (requestDto.getPw().equals(board.getPw())) {
            // board 객체를 입력 받은 값으로 변경
            board.update(requestDto);
            // board를 저장
            Board savedBoard = boardRepository.save(board);
            // Dto로 반환
            return new BoardOneResponserDto("ture", HttpStatus.OK.value(), savedBoard);
        }
        // 비밀번호 불일치
        else {
            return new ResponseDto("false", HttpStatus.NOT_FOUND.value());
        }

    }

    @Transactional
    public ResponseDto deleteBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        // 비밀번호 일치
        if (requestDto.getPw().equals(board.getPw())) {
            // 게시글 삭제
            boardRepository.deleteById(id);
            // ResponseDto로 반환
            return new ResponseDto("true", HttpStatus.OK.value());
        }
        // 비밀번호 불일치
        else {
            return new ResponseDto("false", HttpStatus.NOT_FOUND.value());
        }
    }
}
