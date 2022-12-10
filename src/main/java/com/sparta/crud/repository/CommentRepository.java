package com.sparta.crud.repository;

import com.sparta.crud.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUserId(Long cmtId, Long UserId);
}
