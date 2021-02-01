package com.redbyte.platform.authcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * @author wangwq
 */
@Configuration
public class Oauth2ServerConfig {


    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory keyStore = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStore.getKeyPair("jwt", "123456".toCharArray());
    }
}
