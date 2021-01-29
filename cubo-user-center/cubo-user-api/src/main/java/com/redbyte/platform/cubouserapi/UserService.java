package com.redbyte.platform.cubouserapi;

import com.redbyte.platform.common.Response;
import com.redbyte.platform.cubouserapi.dto.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 *
 * </p>
 *
 * @author wangwq
 */
@FeignClient(value = "cubo-user-center", path = "/micro/user")
public interface UserService {

    /**
     * 获取token
     * @return
     */
    @GetMapping("/getToken")
    Response<String> getToken(String userName, String password);

    /**
     * 验真token
     * @return
     */
    @GetMapping("/verifyToken")
    Response<Boolean> verifyToken(String token);

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/getUserInfo")
    Response<UserInfoDTO> getUserInfo(String uid);
}
