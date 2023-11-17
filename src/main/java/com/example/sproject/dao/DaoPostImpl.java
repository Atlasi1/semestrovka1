package com.example.sproject.dao;

import com.example.sproject.service.ConnectionProvider;
import com.example.sproject.entities.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPostImpl implements Dao<Post>{
    Connection connection;

    public DaoPostImpl() {
        connection = ConnectionProvider.getConn();
    }

    @Override
    public Post get(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from posts where id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            return getList(rs).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Post> getIntrestingPosts() {
        try {
         PreparedStatement ps = connection.prepareStatement("SELECT p.*\n" +
                 "FROM posts p\n" +
                 "LEFT JOIN (\n" +
                 "    SELECT \"to\", COUNT(*) AS like_count\n" +
                 "    FROM likes\n" +
                 "    GROUP BY \"to\"\n" +
                 ") l ON p.id = l.\"to\"\n" +
                 "ORDER BY l.like_count DESC NULLS LAST\n" +
                 "LIMIT 3;");
         ResultSet rs = ps.executeQuery();
         return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> getIntrestingPostsWithCategory(long cat_id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT p.*\n" +
                    "FROM posts p\n" +
                    "JOIN (\n" +
                    "    SELECT \"to\", COUNT(*) as like_count\n" +
                    "    FROM likes\n" +
                    "    GROUP BY \"to\"\n" +
                    "    ORDER BY like_count DESC\n" +
                    ") l ON p.id = l.\"to\" WHERE p.category_id = ? LIMIT 3;");
            ps.setLong(1, cat_id);
            ResultSet rs = ps.executeQuery();
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> getRecentPosts() {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT *\n" +
                    "FROM posts\n" +
                    "ORDER BY date DESC\n" +
                    "LIMIT 5;");
            ResultSet rs = ps.executeQuery();
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> getRecentPostsWithCategory(long cat_id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT *\n" +
                    "FROM posts\n" +
                    "WHERE category_id = ?\n" +
                    "ORDER BY date DESC\n" +
                    "LIMIT 5;");
            ps.setLong(1, cat_id);
            ResultSet rs = ps.executeQuery();
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> getPostsByLimit(long currentPage, long recordsPerPage) {
        long start = currentPage * recordsPerPage - recordsPerPage > 0? currentPage * recordsPerPage - recordsPerPage: 0;
        try {
            String query = "select * from posts offset ? limit ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, start);
            ps.setLong(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> getPostsByLimitWithCategory(long currentPage, long recordsPerPage, long cat_id) {
        long start = currentPage * recordsPerPage - recordsPerPage > 0? currentPage * recordsPerPage - recordsPerPage: 0;
        try {
            String query = "select * from posts where category_id = ? offset ? limit ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, cat_id);
            ps.setLong(2, start);
            ps.setLong(3, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getCountOfRows() {
        try {
            String query = "select count(id) from posts";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getCountOfRowsWithCategory(long cat_id) {
        try {
            String query = "select count(id) from posts where category_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, cat_id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getAll() {
        try {
            String query = "select * from posts";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> getAllWithCategory(Long id_cat) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from posts where category_id = " + id_cat);
            ResultSet rs = ps.executeQuery();
            return getList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(Post x) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into posts(title, description, date, filename, user_id, category_id) values (?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, x.getTitle());
            ps.setString(2, x.getContent());
            ps.setDate(3, x.getDate());
            ps.setString(4, x.getFilename());
            ps.setLong(5, x.getCreatorId());
            ps.setLong(6, x.getCategoryId());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void update(Post post, String[] params) {

    }

    @Override
    public void delete(long id) {

    }

    private List<Post> getList(ResultSet rs) {
        List<Post> posts = new ArrayList<>();
        try {
            while (rs.next()) {
                posts.add(new Post(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("date"),
                        rs.getString("filename"),
                        rs.getLong("user_id"),
                        (long) rs.getInt("category_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return posts;
    }
}
