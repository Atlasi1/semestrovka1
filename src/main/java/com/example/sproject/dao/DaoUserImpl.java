package com.example.sproject.dao;

import com.example.sproject.service.ConnectionProvider;
import com.example.sproject.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoUserImpl implements Dao<User> {
    Connection connection;

    public DaoUserImpl() {
        connection = ConnectionProvider.getConn();
    }

    @Override
    public User get(long id) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("select * from users where id = ?");

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        null,
                        rs.getString("avatar")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User getByEmailAndPassword(String email, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users where email = ? and password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        null,
                        rs.getString("avatar")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User x) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into users(username, email, password, avatar) values (?, ?, ?, ?)");
            ps.setString(1, x.getUsername());
            ps.setString(2, x.getEmail());
            ps.setString(3, x.getPassword());
            ps.setString(4, x.getPhoto());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(User user, String[] params) {
        try {
            PreparedStatement ps = connection.prepareStatement("update users set username = ?, email = ?, password = ?, avatar = ? where id = ?");
            ps.setString(1, params[0]);
            ps.setString(2, params[1]);
            ps.setString(3, params[2]);
            ps.setString(4, params[3]);
            ps.setLong(5, user.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {

    }

    public int countByParameter(String param, String value) {
        try {
            PreparedStatement ps = connection.prepareStatement("select count(*) from users where ? = ?");
            ps.setString(1, param);
            ps.setString(2, value);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
