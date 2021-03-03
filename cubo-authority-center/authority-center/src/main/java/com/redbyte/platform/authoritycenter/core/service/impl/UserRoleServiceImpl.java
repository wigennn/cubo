package com.redbyte.platform.authoritycenter.core.service.impl;

import com.redbyte.platform.authoritycenter.core.entity.UserRole;
import com.redbyte.platform.authoritycenter.core.dao.UserRoleMapper;
import com.redbyte.platform.authoritycenter.core.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author wangwq
 * @since 2021-03-03
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
