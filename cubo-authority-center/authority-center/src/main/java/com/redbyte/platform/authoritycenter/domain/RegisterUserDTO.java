package com.redbyte.platform.authoritycenter.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@Data
public class RegisterUserDTO implements Serializable {
    private static final long serialVersionUID = -2007015808309854530L;

    private String userName;

    private String name;

    private String password;

    private String email;

    private String phone;

    private Integer gender;
}
