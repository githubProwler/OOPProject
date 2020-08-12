package com.kvernadze.project.dao;

import com.kvernadze.project.model.Question;
import com.kvernadze.project.model.Quizz;

import java.util.Collection;

public interface QuizzDao {
    public Quizz get(String name);
    public Quizz get(int quizzId);
    public boolean create(Quizz quizz);
    public boolean addQuestions(Collection<Question> questions, int quizzId);
    public Collection<Quizz> getAll();
    public Collection<Question> getQuestionsByQuizzId(int id);

}
