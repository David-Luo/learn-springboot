package xin.luowei.demo.springboot.practice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Configuration
public class EncryptionConfig {

    @Value("${encryption.privateKey}")
    private String key;
    @Value("${encryption.salt}")
    private String salt;

    @Bean
    public TextEncryptor getEncryptor(){
        return    Encryptors.queryableText(key,  salt);
    }
}