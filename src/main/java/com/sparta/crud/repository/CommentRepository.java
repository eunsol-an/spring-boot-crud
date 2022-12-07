package com.sparta.crud.repository;

import com.sparta.crud.entity.Board;
import com.sparta.crud.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBoardIdOrderByCreatedAtDesc(Board board);

    Optional<Comment> findByIdAndUsername(Long cmtId, String username);
}
