package com.exam.exambook.service;

import com.exam.exambook.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizSet();

    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);
}
