package com.gonzik28.community.dto.utils;

import com.gonzik28.community.dto.RequestUserDto;
import com.gonzik28.community.dto.ResponseUserDto;
import com.gonzik28.community.entity.RoleEntity;
import com.gonzik28.community.entity.UserEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserUtils {
    public static ResponseUserDto userEntityToDto(UserEntity userEntity) {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setLogin(userEntity.getLogin());
        responseUserDto.setName(userEntity.getName());
        responseUserDto.setPassword(userEntity.getPassword());
        responseUserDto.setRoleId(userEntity.getRoles()
                .stream().map(RoleEntity :: getId).collect(Collectors.toList()));
        return responseUserDto;
    }

    public static List<ResponseUserDto> userEntityToDto(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(UserUtils :: userEntityToDto)
                .collect(Collectors.toList());
    }


    public static UserEntity userDtoToEntity(ResponseUserDto responseUserDto, List<RoleEntity> roles) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(responseUserDto.getLogin());
        userEntity.setPassword(responseUserDto.getPassword());
        userEntity.setName(responseUserDto.getName());
        userEntity.setRoles(roles);
        return userEntity;
    }

    ///////////////////////////////////////////////////////////
    public static RequestUserDto userEntityToDtoReq(UserEntity userEntity) {
        RequestUserDto requestUserDto = new RequestUserDto();
        requestUserDto.setLogin(userEntity.getLogin());
        requestUserDto.setName(userEntity.getName());
        requestUserDto.setPassword(userEntity.getPassword());
        requestUserDto.setRoleId(
                userEntity.getRoles()
                        .stream()
                        .map(RoleEntity :: getId)
                        .collect(Collectors.toList()));
        return requestUserDto;
    }

    public static List<RequestUserDto> userEntityToDtoReq(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(UserUtils :: userEntityToDtoReq)
                .collect(Collectors.toList());
    }

    public static UserEntity userDtoReqToEntity(RequestUserDto requestUserDto, Set<RoleEntity> roleEntities) {
        UserEntity userEntity = new UserEntity();
        requestUserDto.setLogin(requestUserDto.getLogin());
        requestUserDto.setName(requestUserDto.getName());
        requestUserDto.setPassword(requestUserDto.getPassword());
        requestUserDto.setRoleId(roleEntities.stream().map(RoleEntity :: getId).collect(Collectors.toList()));
        return userEntity;
    }
}
