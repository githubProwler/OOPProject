package com.kvernadze.project.dao;

import com.kvernadze.project.model.Question;
import com.kvernadze.project.model.Quizz;
import com.kvernadze.project.model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Component("quizzes")
public class SQLQuizzDao implements QuizzDao {
//    private static SQLUserDao instance;

    private Connection conn;

    public SQLQuizzDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/quizzapp?user=Java&password=12345678");
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Quizz get(String name) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "SELECT id, name, description, user FROM quizzes WHERE name = ?;");
            stm.setString(1, name);
            ResultSet res = stm.executeQuery();
            if (!res.next()) {
                stm.close();
                return null;
            }
            Quizz ret = new Quizz(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("description"),
                    res.getInt("user"));
            stm.close();
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Quizz quizz) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "INSERT INTO quizzes (name, description, user) VALUES (?, ?, ?)");
            stm.setString(1, quizz.getName());
            stm.setString(2, quizz.getDescription());
            stm.setInt(3, quizz.getUserId());
            int rowsUpdated = stm.executeUpdate();
            stm.close();
            assert (rowsUpdated == 1);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addQuestions(Collection<Question> questions, int quizzId) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "INSERT INTO questions (question, answer, quizz) VALUES (?, ?, ?)");
            for (Question question : questions) {
                stm.setString(1, question.getQuestion());
                stm.setString(2, question.getAnswer());
                stm.setInt(3, quizzId);
                int rowsUpdated = stm.executeUpdate();
            }
            stm.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection<Quizz> getAll() {
        Collection<Quizz> ret = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "SELECT id, name, description, user FROM quizzes;");
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                ret.add(new Quizz(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getString("description"),
                        res.getInt("user")));
            }
            stm.close();
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Quizz get(int quizzId) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "SELECT id, name, description, user FROM quizzes WHERE id = ?;");
            stm.setInt(1, quizzId);
            ResultSet res = stm.executeQuery();
            if (!res.next()) {
                stm.close();
                return null;
            }
            Quizz ret = new Quizz(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("description"),
                    res.getInt("user"));
            stm.close();
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Question> getQuestionsByQuizzId(int id) {
        Collection<Question> ret = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "SELECT question, answer FROM questions WHERE quizz = ?;");
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                ret.add(new Question(
                        res.getString("question"),
                        res.getString("answer")));
            }
            stm.close();
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
