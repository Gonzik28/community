package com.gonzik28.community.service;

import com.gonzik28.community.entity.RoleEntity;
import com.gonzik28.community.repository.RoleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class RoleEntityServiceImpl implements RoleEntityService {

    private RoleEntityRepository roleRepository;


    @Autowired
    public RoleEntityServiceImpl(RoleEntityRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity getEntityById(String id) {
        return this.roleRepository.findById(id).get();
    }

    @Override
    public List<RoleEntity> getAllEntities() {
        List<RoleEntity> list = new ArrayList<>();
        roleRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public RoleEntity addEntity(RoleEntity entity) {
        try {
            return this.roleRepository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateEntity(RoleEntity entity) {
        try {
            this.roleRepository.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteEntityById(String id) {
        try {
            this.roleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
