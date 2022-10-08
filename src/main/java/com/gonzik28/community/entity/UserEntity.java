package com.gonzik28.community.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = UserEntity.TABLE)
@XmlRootElement(name = UserEntity.TABLE)
public class UserEntity {
    public static final String TABLE = "users";

    @Id
    private String login;
    private String name;
    private String password;

    @ManyToMany(cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "login_user") },
            inverseJoinColumns = { @JoinColumn(name = "id_role") }
    )
    @XmlElement
    List<RoleEntity> roles = new ArrayList<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

}
