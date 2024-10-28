package com.example.board.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.board.DataNotFoundException;
import com.example.board.entity.Question;
import com.example.board.repository.QuestionRepository;


@Service
public class QuestionService {
    
   


   @Autowired
   private QuestionRepository questionRepository;



    public void create(String subject, String content ,String author){
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setAuthor(author);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }


    public void deleteQuestion(Integer id){
      questionRepository.deleteById(id);
    }
    


    public Question getQuestion(Integer id){
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

}
