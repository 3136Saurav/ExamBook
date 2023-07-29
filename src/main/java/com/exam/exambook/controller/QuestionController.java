package com.exam.exambook.controller;

import com.exam.exambook.model.Question;
import com.exam.exambook.model.Quiz;
import com.exam.exambook.service.QuestionService;
import com.exam.exambook.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        for (Question question : questionList) {
            question.setAnswer("");
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

    @PostMapping("/evaluateQuiz")
    public ResponseEntity<?> evaluateQuiz(@RequestBody List<Question> questions) {
        System.out.println(questions);
        AtomicInteger attemptedQuestions = new AtomicInteger();
        AtomicInteger correctAnswers = new AtomicInteger();

        double singleQuestionMark = (double) (questions.get(0).getQuiz().getMaximumMarks() / questions.size());
        questions.forEach((question -> {
            Question ques = questionService.getQuestion(question.getId());
            if (ques.getAnswer().equals(question.getGivenAnswer())) {
                correctAnswers.getAndIncrement();
            }

            if (question.getGivenAnswer() != null) {
                attemptedQuestions.getAndIncrement();
            }
        }));

        double score = Math.ceil(correctAnswers.doubleValue() * (double)singleQuestionMark);
        Map<String, Object> map = Map.of("marksScored", score, "correctAnswers", correctAnswers, "attemptedQuestions", attemptedQuestions);
        return ResponseEntity.ok(map);
    }
}

