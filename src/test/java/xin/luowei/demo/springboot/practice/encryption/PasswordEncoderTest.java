package xin.luowei.demo.springboot.practice.encryption;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {
	
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup(){
    	passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    /**
     * 密码加密
     */
    @Test
    public  void testeEncode() {
    	String raw = "pass";
        String encoded =  passwordEncoder.encode(raw);
        System.out.println(encoded);
    }

    /**
     * 密码校验
     *
     * @param raw String 原始密码
     * @param encodePassword String 加密后的密码
     * @return true | false
     */
//    public static boolean matches(String raw, String encodePassword) {
//        return passwordEncoder.matches(raw, encodePassword);
//    }
}
