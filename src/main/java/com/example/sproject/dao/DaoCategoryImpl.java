package com.example.sproject.dao;

import com.example.sproject.entities.Category;
import com.example.sproject.service.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCategoryImpl implements Dao<Category>{
    Connection con;

    public DaoCategoryImpl() {
        con = ConnectionProvider.getConn();
    }
    @Override
    public Category get(long id) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from categories where id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Category(rs.getInt("id"), rs.getString("category_text"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Category getByText(String text) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from categories where category_text = ?");
            ps.setString(1, text);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Category(rs.getInt("id"), rs.getString("category_text"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getAll() {
        try {
            PreparedStatement ps = con.prepareStatement("select * from categories");
            ResultSet rs = ps.executeQuery();
            List<Category> list = new ArrayList<>();
            while(rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("category_text")));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Category x) {
        //
    }

    @Override
    public void update(Category category, String[] params) {
        //
    }

    @Override
    public void delete(long id) {
        //
    }
}
