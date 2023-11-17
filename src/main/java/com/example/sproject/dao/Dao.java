package com.example.sproject.dao;

import java.util.List;

public interface Dao<T> {
    T get(long id);
    List<T> getAll();
    void save(T x);
    void update(T t, String[] params);
    void delete(long id);
}
