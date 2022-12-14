package com.sparta.crud.dto;

import com.sparta.crud.entity.Board;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardToDto {
    private Long id;
    private String title;
    private String username;
    private String content;
    private int boardLikeCount;
    private boolean boardLikeCheck;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private List<CommentToDto> commentList = new ArrayList<>();

    public BoardToDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.content = board.getContent();
        this.boardLikeCount = board.getBoardLikeList().size();
        this.boardLikeCheck = false;
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }

    public BoardToDto(Board board, List<CommentToDto> commentList, boolean boardLikeCheck) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.content = board.getContent();
        this.boardLikeCount = board.getBoardLikeList().size();
        this.boardLikeCheck = boardLikeCheck;
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.commentList = commentList;
    }

}
