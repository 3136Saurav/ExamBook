package com.exam.exambook.service;

import com.exam.exambook.model.Question;
import com.exam.exambook.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Set<Question> getQuestionSet();

    public Question getQuestion(Long questionId);

    public Set<Question> getQuizQuestions(Quiz quiz);

    public void deleteQuestion(Long questionId);
}
