package com.redbyte.platform.authoritycenter;

import com.redbyte.platform.authoritycenter.core.service.RegisterService;
import com.redbyte.platform.authoritycenter.domain.RegisterUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorityCenterApplicationTests {

    @Autowired
    private RegisterService registerService;

    @Test
    void contextLoads() throws Exception {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();

        registerService.save(registerUserDTO);
    }

    @Test
    void registerUserTest() {
        try {
            RegisterUserDTO registerUserDTO = new RegisterUserDTO();
            registerUserDTO.setUserName("13151559017");
            registerUserDTO.setName("王伟庆");
            registerUserDTO.setEmail("wigen96@163.com");
            registerUserDTO.setPassword("123456");
            registerUserDTO.setPhone("13151559017");

            registerService.save(registerUserDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
