package com.example.sproject.service;

import com.example.sproject.dao.DaoLikeImpl;
import com.example.sproject.dao.DaoPostImpl;
import com.example.sproject.entities.Like;

public class LikeService {
    public boolean isLiked(int user_id, int post_id) {
        DaoLikeImpl daoLike = new DaoLikeImpl();
        for(Like like: daoLike.getAll()) {
            if(like.getTo_id() == post_id && like.getFrom_id() == user_id) {
                return true;
            }
        }
        return false;
    }
}
