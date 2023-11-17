package com.example.sproject.entities;

public class Like {
    private long id;
    private long from_id;
    private long to_id;

    public Like(long id, long from_id, long to_id) {
        this.id = id;
        this.from_id = from_id;
        this.to_id = to_id;
    }

    public Like(long from_id, long to_id) {
        this.from_id = from_id;
        this.to_id = to_id;
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

    public long getTo_id() {
        return to_id;
    }

    public void setTo_id(long to_id) {
        this.to_id = to_id;
    }
}
