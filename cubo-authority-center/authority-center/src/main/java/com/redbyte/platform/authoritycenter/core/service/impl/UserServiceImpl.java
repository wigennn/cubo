package com.redbyte.platform.authoritycenter.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.redbyte.platform.authoritycenter.core.entity.Role;
import com.redbyte.platform.authoritycenter.core.entity.User;
import com.redbyte.platform.authoritycenter.core.dao.UserMapper;
import com.redbyte.platform.authoritycenter.core.entity.UserRole;
import com.redbyte.platform.authoritycenter.core.service.IRoleService;
import com.redbyte.platform.authoritycenter.core.service.IUserRoleService;
import com.redbyte.platform.authoritycenter.core.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wangwq
 * @since 2021-03-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleService roleService;

    @Override
    public User saveUserInfo(User user) throws Exception {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        this.save(user);
        return user;
    }

    @Override
    public User findByUserName(String userName) throws Exception {

        User user = this.getOne(new QueryWrapper<User>().lambda()
                .eq(User::getAccount, userName));

        List<Role> roles = queryUserRoles(user.getId());
        user.setRoles(roles);

        return user;
    }

    private List<Role> queryUserRoles(Long userId) {

        List<UserRole> userRoleList = userRoleService.list(new QueryWrapper<UserRole>().lambda()
                .eq(UserRole::getUserId, userId));
        Set<Long> roleIds = userRoleList.stream()
                .map(UserRole::getRoleId).collect(Collectors.toSet());
        List<Role> roles = roleService.list(new QueryWrapper<Role>().lambda().in(Role::getId, roleIds));

        return roles;
    }
}
