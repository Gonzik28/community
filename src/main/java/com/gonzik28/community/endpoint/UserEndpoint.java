package com.gonzik28.community.endpoint;

import com.gonzik28.community.dto.RequestUserDto;
import com.gonzik28.community.dto.ResponseUserDto;
import com.gonzik28.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Map;
import java.util.Set;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private UserRepository userRepository;

    @Autowired
    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findUser")
    @ResponsePayload
    public ResponseUserDto findUser(@RequestPayload String login) {
        return userRepository.findUser(login);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUser")
    @ResponsePayload
    public ResponseUserDto addUser(@RequestPayload RequestUserDto request) {
        return userRepository.addUser(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUser")
    @ResponsePayload
    public ResponseUserDto updateUser(@RequestPayload RequestUserDto request) {
        return userRepository.updateUser(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUser")
    @ResponsePayload
    public ResponseUserDto deleteUser(@RequestPayload String login) {
        return userRepository.deleteUser(login);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findUsers")
    @ResponsePayload
    public Set<Map.Entry<String, ResponseUserDto>> findUsers() {
        return userRepository.findUsers();
    }
}
