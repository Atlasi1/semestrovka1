package com.example.sproject.dao;

import com.example.sproject.entities.Like;
import com.example.sproject.service.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoLikeImpl implements Dao<Like>{
    Connection con;

    public DaoLikeImpl() {
        con = ConnectionProvider.getConn();
    }
    @Override
    public Like get(long id) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from likes where id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Like(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Like> getByPost(long post_id) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from likes where \"to\" = ?");
            ps.setLong(1, post_id);
            ResultSet rs = ps.executeQuery();
            List<Like> likes = new ArrayList<>();
            while (rs.next()) {
                likes.add(new Like(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3)));
            }
            return likes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Like> getAll() {
        try {
            PreparedStatement ps = con.prepareStatement("select * from likes");
            ResultSet rs = ps.executeQuery();
            List<Like> likes = new ArrayList<>();
            while(rs.next()) {
                likes.add(new Like(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3)));
            }
            return likes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Like x) {
        try {
            PreparedStatement ps = con.prepareStatement("insert into likes(\"from\", \"to\") values (?, ?)");
            ps.setLong(1, x.getFrom_id());
            ps.setLong(2, x.getTo_id());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Like like, String[] params) {

    }

    @Override
    public void delete(long id) {

    }
}
