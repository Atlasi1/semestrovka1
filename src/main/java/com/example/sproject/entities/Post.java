package com.example.sproject.entities;

import com.example.sproject.dao.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Post {
    DaoUserImpl daoUser = new DaoUserImpl();
    DaoCategoryImpl daoCategory = new DaoCategoryImpl();
    DaoLikeImpl daoLike = new DaoLikeImpl();
    DaoCommentImpl daoComment = new DaoCommentImpl();
    private long id;
    private String title;
    private String content;

    private Date date;
    private String filename;


    private User creator;

    private long categoryId;
    private String categoryName;
    private List<Like> likes;
    private List<Comment> comments;

    public String getCreatorName() {
        return creator.getUsername();
    }

    public Long getCreatorId() {
        return creator.getId();
    }
    public String getCreatorPhoto() { return creator.getPhoto(); }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getPeaceOfContent() {
        return content.substring(0, Math.min(100, content.length()/3)) + "...";
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryName() { //TODO добавить категории
        return categoryName;
    }

    public Long getCategoryId() {return categoryId;}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getCountOfLikes() {
        return likes.size();
    }

    public int getCountOfComments() {
        return comments.size();
    }

    public List<Like> getLikes() {
        return likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Post(long id, String title, String content, Date date, String filename, Long user_id, Long category_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.filename = filename;
        this.creator = daoUser.get(user_id);
        this.categoryId = category_id;
        this.categoryName = daoCategory.get(category_id).getText();
        this.likes = daoLike.getByPost(id);
        this.comments = daoComment.getByPost(id);
    }

    public Post(String title, String content, Date date, String filename, Long user_id, String category_name) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.filename = filename;
        this.creator = daoUser.get(user_id);
        this.categoryName = category_name;
        this.categoryId = daoCategory.getByText(category_name).getId();
        this.likes = null;
        this.comments = null;
    }


//    public String getPartOfDescription() {
//        return description.substring(0, 20) + "...";
//    }
}
