package xin.luowei.demo.springboot.practice.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户注册服务
 */
@Service
public class RegistorService {

    @Autowired
    private UserRepository userRepository;

    public User registor(User user) {
        return userRepository.save(user);
    }
}