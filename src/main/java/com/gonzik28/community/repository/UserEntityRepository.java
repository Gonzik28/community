package com.gonzik28.community.repository;

import com.gonzik28.community.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, String> {
    public UserEntity findByLogin(String login);

}
