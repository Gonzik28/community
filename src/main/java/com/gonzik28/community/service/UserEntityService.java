package com.gonzik28.community.service;

import com.gonzik28.community.entity.UserEntity;

import java.util.List;

public interface UserEntityService {

    public UserEntity getEntityById(String login);
    public List<UserEntity> getAllEntities();
    public UserEntity addEntity(UserEntity entity);
    public boolean updateEntity(UserEntity entity);
    public boolean deleteEntityById(String login);
}
