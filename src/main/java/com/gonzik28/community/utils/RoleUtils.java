package com.gonzik28.community.utils;

import com.gonzik28.community.entity.RoleEntity;
import com.gonzik28.community.gen.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleUtils {

    public static Role roleEntityToDto(RoleEntity roleEntity) {
        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());
        return role;
    }

    public static List<Role> roleEntityToDtos(List<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(RoleUtils :: roleEntityToDto)
                .collect(Collectors.toList());
    }


}
