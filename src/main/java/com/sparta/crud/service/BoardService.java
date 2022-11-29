package com.sparta.crud.service;

import com.sparta.crud.dto.BoardRequestDto;
import com.sparta.crud.dto.BoardResponseDto;
import com.sparta.crud.entity.Board;
import com.sparta.crud.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board, true);
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoardList() {
        List<Board> boardList = boardRepository.findAllByOrderByCreatedAtDesc();
        List<BoardResponseDto> boardResponseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            boardResponseDtoList.add(new BoardResponseDto(board, true));
        }
        return boardResponseDtoList;
    }

    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new BoardResponseDto(board, true);
    }

//    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto) {
        // 새로운 board 객체 추가
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        // board 객체를 영속화
        Board savedBoard = boardRepository.save(board);

        // 여기서 비밀번호 일치하는지 확인
        if (requestDto.getPw().equals(board.getPw())) {
            // 비밀번호 일치

            // board 객체를 입력 받은 값으로 변경
            savedBoard.update(requestDto);
            // board를 저장
            Board resultBoard = boardRepository.save(savedBoard);
            // ResponseDto로 반환
            return new BoardResponseDto(resultBoard, true);
        } else {
            // 비밀번호 불일치

            return new BoardResponseDto(false);
        }

    }

    @Transactional
    public BoardResponseDto deleteBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (requestDto.getPw().equals(board.getPw())) {
            // 비밀번호 일치

            // 게시글 삭제
            boardRepository.deleteById(id);
            // ResponseDto로 반환
            return new BoardResponseDto(true);
        } else {
            // 비밀번호 불일치

            return new BoardResponseDto(false);
        }
    }
}
