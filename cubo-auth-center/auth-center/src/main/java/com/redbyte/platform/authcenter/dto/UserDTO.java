package com.redbyte.platform.authcenter.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangwq
 */
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -1468828711465125211L;

    private Long uid;
    private String name;
    private String password;
    private String role;

    public UserDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
