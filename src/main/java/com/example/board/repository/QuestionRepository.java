package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
}
