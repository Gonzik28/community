package com.gonzik28.community.repository;

import com.gonzik28.community.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleEntityRepository extends CrudRepository<RoleEntity, String> {
}
