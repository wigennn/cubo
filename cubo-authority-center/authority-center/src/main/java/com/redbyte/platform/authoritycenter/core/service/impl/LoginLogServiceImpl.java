package com.redbyte.platform.authoritycenter.core.service.impl;

import com.redbyte.platform.authoritycenter.core.entity.LoginLog;
import com.redbyte.platform.authoritycenter.core.dao.LoginLogMapper;
import com.redbyte.platform.authoritycenter.core.service.ILoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登陆日志表 服务实现类
 * </p>
 *
 * @author wangwq
 * @since 2021-03-03
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

}
