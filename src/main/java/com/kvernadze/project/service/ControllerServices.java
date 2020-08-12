package com.kvernadze.project.service;

import com.kvernadze.project.dao.QuizzDao;
import com.kvernadze.project.dao.SQLQuizzDao;
import com.kvernadze.project.dao.SQLUserDao;
import com.kvernadze.project.dao.UserDao;
import com.kvernadze.project.model.Question;
import com.kvernadze.project.model.Quizz;
import com.kvernadze.project.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

public class ControllerServices {
    private static UserDao users;
    private static QuizzDao quizzes;

    public ControllerServices() {
        //ToDo init
        users = new SQLUserDao();
        quizzes = new SQLQuizzDao();
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public boolean create(User user) {
        if (checkValidity(user)) {
            return false;
        }
        user.setPassword(generatePasswordHash(user.getPassword()));
        return users.create(user);
    }

    private boolean checkValidity(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (username.matches("^[a-zA-Z0-9-_]+$") && password.length() > 0) {
            return false;
        }

        return true;
    }

    public boolean checkPassword(String username, String password) {
        return users.checkPassword(username, generatePasswordHash(password));
    }

    public static String generatePasswordHash(String passwordToHash) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length; i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public Collection<User> getAllUsersExceptSelf(String username) {
        Collection<User> ret;
        User rem = null;
        ret = users.getAll();
        for (User user : ret) {
            if (user.getUsername().equals(username)) {
                rem = user;
            }
        }
        ret.remove(rem);

        return ret;
    }

    public void deleteUser(String username) {
        users.delete(username);
    }

    public void upgradeUserType(String username) {
        users.update(username, 0);
    }

    public void downgradeUserType(String username) {
        users.update(username, 1);
    }

    public void addQuizz(Quizz quizz) {
        quizzes.create(quizz);
    }

    public Quizz getQuizz(String name) {
        return quizzes.get(name);
    }

    public void addQuestions(Collection<Question> questions, int id) {
        quizzes.addQuestions(questions, id);
    }

    public Collection<Quizz> getAllQuizzes() {
        Collection<Quizz> ret;
        User rem = null;
        ret = quizzes.getAll();
        return ret;
    }

    public Quizz getQuizzById(int quizzid) {
        return quizzes.get(quizzid);
    }

    public Collection<Question> getQuestionsForQuizz(int quizzid) {
        return quizzes.getQuestionsByQuizzId(quizzid);
    }
}
