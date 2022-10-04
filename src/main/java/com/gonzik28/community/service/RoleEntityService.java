package com.gonzik28.community.service;

import com.gonzik28.community.entity.RoleEntity;
import com.gonzik28.community.entity.UserEntity;

import java.util.List;

public interface RoleEntityService {

    public RoleEntity getEntityById(String id);
    public List<RoleEntity> getAllEntities();
    public RoleEntity addEntity(RoleEntity entity);
    public boolean updateEntity(RoleEntity entity);
    public boolean deleteEntityById(String id);
}
