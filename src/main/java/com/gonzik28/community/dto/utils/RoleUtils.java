package com.gonzik28.community.dto.utils;

import com.gonzik28.community.dto.RequestRoleDto;
import com.gonzik28.community.dto.ResponseRoleDto;
import com.gonzik28.community.entity.RoleEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RoleUtils {
    public static ResponseRoleDto roleEntityToDto(RoleEntity roleEntity) {
        ResponseRoleDto responseRoleDto = new ResponseRoleDto();
        responseRoleDto.setId(roleEntity.getId());
        responseRoleDto.setName(roleEntity.getName());
        return responseRoleDto;
    }

    public static List<ResponseRoleDto> roleEntityToDto(Collection<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(RoleUtils :: roleEntityToDto)
                .collect(Collectors.toList());
    }

    public static RoleEntity roleDtoToEntity(ResponseRoleDto responseRoleDto) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(responseRoleDto.getId());
        roleEntity.setName(responseRoleDto.getName());
        return roleEntity;
    }

    public static List<RoleEntity> roleDtoToEntity(Collection<ResponseRoleDto> responseRoleDtos) {
        return responseRoleDtos.stream()
                .map(RoleUtils :: roleDtoToEntity)
                .collect(Collectors.toList());
    }

////////////////////

    public static RequestRoleDto roleEntityToReqDto(RoleEntity roleEntity) {
        RequestRoleDto requestRoleDto = new RequestRoleDto();
        requestRoleDto.setId(roleEntity.getId());
        requestRoleDto.setName(roleEntity.getName());
        return requestRoleDto;
    }

    public static List<RequestRoleDto> roleEntityToReqDto(Collection<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(RoleUtils :: roleEntityToReqDto)
                .collect(Collectors.toList());
    }

    public static RoleEntity roleReqDtoToEntity(RequestRoleDto requestRoleDto) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(requestRoleDto.getId());
        roleEntity.setName(requestRoleDto.getName());
        return roleEntity;
    }

    public static List<RoleEntity> roleReqDtoToEntity(Collection<RequestRoleDto> requestRoleDtos) {
        return requestRoleDtos.stream()
                .map(RoleUtils :: roleReqDtoToEntity)
                .collect(Collectors.toList());
    }
}
