package com.example.sproject.entities;

import com.example.sproject.dao.DaoPostImpl;
import com.example.sproject.dao.DaoUserImpl;

public class Comment {
    private long id;
    private long from_id;
    private long to_id;
    private String text;

    private User writer;

    public Comment(long from_id, long to_id, String text) {
        this.from_id = from_id;
        this.to_id = to_id;
        this.text = text;
        DaoUserImpl daoUser = new DaoUserImpl();
        this.writer = daoUser.get(from_id);
    }

    public Comment(long id, long from_id, long to_id, String text) {
        this.id = id;
        this.from_id = from_id;
        this.to_id = to_id;
        this.text = text;
        DaoUserImpl daoUser = new DaoUserImpl();
        this.writer = daoUser.get(from_id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFrom_id() {
        return from_id;
    }

    public void setFrom_id(long from_id) {
        this.from_id = from_id;
    }

    public String getFrom_name() {
        return writer.getUsername();
    }

    public long getTo_id() {
        return to_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getWriter() {
        return writer;
    }
}
