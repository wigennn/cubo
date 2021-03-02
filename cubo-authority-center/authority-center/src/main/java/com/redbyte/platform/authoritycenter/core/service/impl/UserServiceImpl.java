package com.redbyte.platform.authoritycenter.core.service.impl;

import com.redbyte.platform.authoritycenter.core.entity.User;
import com.redbyte.platform.authoritycenter.core.dao.UserMapper;
import com.redbyte.platform.authoritycenter.core.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wangwq
 * @since 2021-03-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
