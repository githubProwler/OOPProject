package com.kvernadze.project.dao;

import com.kvernadze.project.model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Component("users")
public class SQLUserDao implements UserDao {
//    private static SQLUserDao instance;

    private Connection conn;

//    public static SQLUserDao getInstance() throws SQLException, ClassNotFoundException {
//        if (instance == null) {
//            synchronized (SQLUserDao.class) {
//                if (instance == null) {
//                    instance = new SQLUserDao();
//                }
//            }
//        }
//        return instance;
//    }

    public SQLUserDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/quizzapp?user=Java&password=12345678");
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(String username) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "SELECT id, username, password, usertype FROM users WHERE username = ?;");
            stm.setString(1, username);
            ResultSet res = stm.executeQuery();
            if (!res.next()) {
                stm.close();
                return null;
            }
            User ret = new User(
                    res.getInt("id"),
                    res.getString("username"),
                    res.getInt("usertype"));
            stm.close();
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkPassword(String username, String password) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "SELECT password FROM users WHERE username = ?;");
            stm.setString(1, username);
            ResultSet res = stm.executeQuery();
            if (!res.next()) {
                stm.close();
                return false;
            }

            boolean ret = res.getString("password").equals(password);
            stm.close();
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public User get(int id) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "SELECT id, username, usertype FROM users WHERE id = ?;");
            stm.setInt(1, id);
            ResultSet res = stm.executeQuery();
            if (!res.next()) {
                stm.close();
                return null;
            }
            User ret = new User(
                    res.getInt("id"),
                    res.getString("username"),
                    res.getInt("usertype"));
            stm.close();
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> ret = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "SELECT id, username, usertype FROM users;");
            ResultSet res = stm.executeQuery();
            while (res.next()) {
                ret.add(new User(
                        res.getInt("id"),
                        res.getString("username"),
                        res.getInt("usertype")));
            }
            stm.close();
            return ret;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(User user) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "INSERT INTO users (username, password, usertype) VALUES (?, ?, ?)");
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setInt(3, user.getUserType());
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
    public void update(String username, int userType) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "UPDATE users SET usertype = ? WHERE username = ?");
            stm.setInt(1,userType);
            stm.setString(2, username);
            int rowsDeleted = stm.executeUpdate();
            stm.close();
            assert (rowsDeleted == 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(String username) {
        PreparedStatement stm = null;
        try {
            stm = conn.prepareStatement(
                    "DELETE FROM users WHERE username = ?");
            stm.setString(1, username);
            int rowsDeleted = stm.executeUpdate();
            stm.close();
            assert (rowsDeleted == 1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}