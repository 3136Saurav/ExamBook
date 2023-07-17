package com.exam.exambook.controller;

import com.exam.exambook.model.Question;
import com.exam.exambook.model.Quiz;
import com.exam.exambook.service.QuestionService;
import com.exam.exambook.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }

    @RequestMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuizQuestions(@PathVariable("quizId") Long quizId) {
        Quiz quiz = quizService.getQuiz(quizId);
        Set<Question> questionSet = quiz.getQuestionSet();
        List<Question> questionList = new ArrayList<>(questionSet);
        if (questionList.size() > quiz.getNumberOfQuestions()) {
            questionList = questionList.subList(0, quiz.getNumberOfQuestions());
        }
        Collections.shuffle(questionList);
        return ResponseEntity.ok(questionList);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestion(@PathVariable("questionId") Long questionId) {
        return ResponseEntity.ok(questionService.getQuestion(questionId));
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok("Question deleted!");
    }
}

