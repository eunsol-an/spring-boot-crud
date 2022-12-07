package com.sparta.crud.entity;

import com.sparta.crud.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID", nullable = false)
    private Board board;

    public Comment(CommentRequestDto commentRequestDto, String username, Board board) {
        this.comment = commentRequestDto.getComment();
        this.username = username;
        this.board = board;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
