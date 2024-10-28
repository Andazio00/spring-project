package com.example.board.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.example.board.Service.QuestionService;
import com.example.board.entity.Question;
import com.example.board.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;




@RequiredArgsConstructor
@Controller
public class QuestionController {
    
    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    private final QuestionService questionService;

    @GetMapping("/question/lists")
    public String lists(Model model){
        List<Question> questionLists = this.questionRepository.findAll();
        model.addAttribute("questionLists", questionLists);
        return "question_list";
    }


    // 사용자가 글쓰기 페이지로 들어가게 해줌 
    @GetMapping("/question/create")
    public String questionCreate(){
        return "question_form";
    }

    @GetMapping("/question/lists/id")
    public String getMethodName() {
        return new String();
    }
    

    // 폼으로부터 받은 요청 처리하는 메소드
    @PostMapping("/question/create")
    public String questionCreate(@RequestParam(value = "subject")String subject, @RequestParam(value = "content") String content , @RequestParam(value="author")String author){
        //  42번 
        this.questionService.create(subject, content,author);
        return "redirect:/question/lists";
    }
    

    
    @GetMapping(value = "/question/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
    

    @GetMapping("/question/delete/{id}")
    public String questionDelete(@PathVariable("id") Integer id) {
        questionService.deleteQuestion(id);
        return "redirect:/question/lists"; // 삭제 후 목록으로 리다이렉트
    }

}
