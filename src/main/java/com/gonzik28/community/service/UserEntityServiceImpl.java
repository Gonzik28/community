package com.gonzik28.community.service;

import com.gonzik28.community.entity.UserEntity;
import com.gonzik28.community.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class UserEntityServiceImpl implements UserEntityService{

    private UserEntityRepository userRepository;

    @Autowired
    public UserEntityServiceImpl(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getEntityById(String login) {
        UserEntity userEntity = this.userRepository.findByLogin(login);
        userEntity.getRoles().iterator();
        return userEntity;
    }

    @Override
    public List<UserEntity> getAllEntities() {
        List <UserEntity> list = new ArrayList<>();
        userRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public UserEntity addEntity(UserEntity entity) {
        try {
            return this.userRepository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateEntity(UserEntity entity) {
        try {
            this.userRepository.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEntityById(String login) {
        try {
            this.userRepository.deleteById(login);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
