package com.redbyte.platform.authoritycenter.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.redbyte.platform.authoritycenter.core.entity.User;
import com.redbyte.platform.authoritycenter.core.service.IUserService;
import com.redbyte.platform.authoritycenter.core.service.RegisterService;
import com.redbyte.platform.authoritycenter.domain.RegisterUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisterUserDTO registerUserDTO) throws Exception {

        User user = new User();
        user.setAccount(registerUserDTO.getUserName());
        user.setName(registerUserDTO.getName());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setEmail(registerUserDTO.getEmail());
        user.setPhone(registerUserDTO.getPhone());

        if (userService.findByUserName(registerUserDTO.getUserName()) != null) {
            throw new Exception("用户已存在");
        }

        if (userService.getOne(new QueryWrapper<User>().lambda().eq(User::getPhone, registerUserDTO.getPhone())) != null) {
            throw new Exception("手机号已注册");
        }

        userService.save(user);
    }
}
