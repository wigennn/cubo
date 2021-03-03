package com.redbyte.platform.authoritycenter.core.service.impl;

import com.redbyte.platform.authoritycenter.core.entity.Role;
import com.redbyte.platform.authoritycenter.core.dao.RoleMapper;
import com.redbyte.platform.authoritycenter.core.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wangwq
 * @since 2021-03-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
