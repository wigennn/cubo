package com.redbyte.platform.authoritycenter.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.redbyte.platform.authoritycenter.core.dao.UserMapper;
import com.redbyte.platform.authoritycenter.core.entity.User;
import com.redbyte.platform.authoritycenter.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = null;
        try {
            user = userService.findByUserName(s);
        } catch (Exception e) {
            throw new UsernameNotFoundException("根据用户名查询用户信息异常!");
        }

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        user.getRoles().stream().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new LoginUserDetail(authorityList, user.getPassword(), user.getAccount());
    }

}
