package com.gonzik28.community.endpoint;

import com.gonzik28.community.entity.UserEntity;
import com.gonzik28.community.gen.*;
import com.gonzik28.community.service.UserEntityService;
import com.gonzik28.community.utils.RoleUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class UserEndpoint {
    public static final String NAMESPACE_URI = "http://www.gonzik28.com/community/gen";

    private UserEntityService service;

    public UserEndpoint() {

    }

    @Autowired
    public UserEndpoint(UserEntityService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByLoginRequest")
    @ResponsePayload
    public GetUserByLoginResponse getUserByLogin(@RequestPayload GetUserByLoginRequest request) {
        GetUserByLoginResponse response = new GetUserByLoginResponse();
        UserEntity userEntity = service.getEntityById(request.getLogin());
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        List<Role> rolesUser = RoleUtils.roleEntityToDtos(userEntity.getRoles());
        user.setCurrency(rolesUser.get(0));
        response.setUser(user);
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {
        GetAllUsersResponse response = new GetAllUsersResponse();
        List<User> usersList = new ArrayList<>();
        List<UserEntity> userEntityList = service.getAllEntities();
        for (UserEntity entity : userEntityList) {
            User user = new User();
            BeanUtils.copyProperties(entity, user);
            usersList.add(user);
        }
        response.getUser().addAll(usersList);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse addUser(@RequestPayload AddUserRequest request) {
        AddUserResponse response = new AddUserResponse();
        User user = new User();
        ServiceStatus serviceStatus = new ServiceStatus();

        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setLogin(request.getLogin());
        userEntity.setPassword(request.getPassword());
//        userEntity.setRoles(request.getRole());
        userEntity = service.addEntity(userEntity);

        if (userEntity == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding Entity");
        } else {
            BeanUtils.copyProperties(userEntity, user);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
        }

        response.setUser(user);
        response.setServiceStatus(serviceStatus);
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateMovie(@RequestPayload UpdateUserRequest request) {
        UpdateUserResponse response = new UpdateUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        UserEntity userFromDB = service.getEntityById(request.getLogin());

        if (userFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Movie = " + request.getName() + " not found");
        } else {

            // 2. Get updated movie information from the request
            userFromDB.setName(request.getName());
            userFromDB.setPassword(request.getPassword());
//            userFromDB.setRoles(request.getRole());
            // 3. update the movie in database

            boolean flag = service.updateEntity(userFromDB);

            if (flag == false) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating Entity=" + request.getName());
            } else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Content updated Successfully");
            }
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteMovie(@RequestPayload DeleteUserRequest request) {
        DeleteUserResponse response = new DeleteUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean flag = service.deleteEntityById(request.getLogin());

        if (flag == false) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deletint Entity id=" + request.getLogin());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

}

