package com.redbyte.platform.authoritycenter.core.service;

import com.redbyte.platform.authoritycenter.domain.RegisterUserDTO;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
public interface RegisterService {

    void save(RegisterUserDTO registerUserDTO) throws Exception;
}
