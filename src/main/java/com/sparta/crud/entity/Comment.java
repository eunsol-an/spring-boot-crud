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

    @ManyToOne
    @JoinColumn(name = "BOARD_ID", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    public Comment(CommentRequestDto commentRequestDto, Board board, User user) {
        this.comment = commentRequestDto.getComment();
        this.username = user.getUsername();
        this.board = board;
        this.user = user;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
