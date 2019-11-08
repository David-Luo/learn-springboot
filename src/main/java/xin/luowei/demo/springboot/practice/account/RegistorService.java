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

    public User registor(User user) throws Exception {
        User save = userRepository.save(user);
        if(true){
            throw new Exception("No Rollback");
        }
        return save;
    }
}