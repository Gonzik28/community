package com.gonzik28.community.repository;

import com.gonzik28.community.dto.RequestUserDto;
import com.gonzik28.community.dto.ResponseUserDto;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UserRepository {
    private static final Map<String, ResponseUserDto> users = new HashMap<>();

    @PostConstruct
    public void initData() {
        // initialize users map
    }

    public Set<Map.Entry<String, ResponseUserDto>> findUsers() {
        return users.entrySet();
    }

    public ResponseUserDto findUser(String login) {
        return users.get(login);
    }

    public ResponseUserDto addUser(RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setLogin(requestUserDto.getLogin());
        responseUserDto.setPassword(requestUserDto.getPassword());
        responseUserDto.setName(requestUserDto.getName());
        responseUserDto.setRoleId(requestUserDto.getRoleId());
        return users.put(responseUserDto.getLogin(), responseUserDto);
    }

    public ResponseUserDto updateUser(RequestUserDto requestUserDto) {
        if(requestUserDto.getLogin().isEmpty()) {
            throw new NullPointerException("Exception: Login is null!");
        }else{
            ResponseUserDto responseUserDto = findUser(requestUserDto.getLogin());
            responseUserDto.setName(requestUserDto.getName());
            responseUserDto.setPassword(requestUserDto.getPassword());
            responseUserDto.setRoleId(requestUserDto.getRoleId());
            return users.put(responseUserDto.getLogin(), responseUserDto);
        }
    }

    public ResponseUserDto deleteUser(String login) {
        return users.remove(login);
    }
}
