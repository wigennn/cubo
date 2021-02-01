package com.redbyte.platform.authcenter.config;

import com.redbyte.platform.authcenter.dto.UserDTO;
import com.redbyte.platform.authcenter.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author wangwq
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    private List<UserDTO> userDTOList;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    public void initAdminData() {
        String password = passwordEncoder.encode("123456");

        userDTOList.add(new UserDTO("wigen", password));
        userDTOList.add(new UserDTO("wmy", password));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return new UserDetailsDTO();
    }
}
