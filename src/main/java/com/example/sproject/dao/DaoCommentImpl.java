package com.example.sproject.dao;

import com.example.sproject.entities.Comment;
import com.example.sproject.service.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCommentImpl implements Dao<Comment> {
    Connection con;

    public DaoCommentImpl() {
        con = ConnectionProvider.getConn();
    }
    @Override
    public Comment get(long id) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from comments where id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Comment(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3),
                        rs.getString(4)
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> getAll() {
        try {
            PreparedStatement ps = con.prepareStatement("select * from comments");
            ResultSet rs = ps.executeQuery();
            List<Comment> comments = new ArrayList<>();
            while(rs.next()) {
                comments.add(new Comment(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3),
                        rs.getString(4)
                ));
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Comment x) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into comments(\"from\", \"to\", text) values (?, ?, ?)");
            ps.setLong(1, x.getFrom_id());
            ps.setLong(2, x.getTo_id());
            ps.setString(3, x.getText());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Comment comment, String[] params) {

    }

    @Override
    public void delete(long id) {

    }

    public List<Comment> getByPost(long id) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from comments where \"to\" = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            List<Comment> comments = new ArrayList<>();
            while(rs.next()) {
                comments.add(new Comment(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3),
                        rs.getString(4)
                ));
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
