package com.gonzik28.community.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "responseRole")
public class ResponseRoleDto {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
