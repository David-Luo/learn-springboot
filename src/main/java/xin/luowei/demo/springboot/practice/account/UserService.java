package xin.luowei.demo.springboot.practice.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 获取用户信息并脱敏
     */
    public User getUser(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent())
            return null;
        User user = optional.get();
        user.setPassword(null);
        return user;
    }

    /**
     * 更新用户普通信息
     */
    public User updateUserInfo(User user) {
        Integer id = user.getId();
        if (id == null) {
            throw new IllegalArgumentException("id不能为空");
        }
        User saved = userRepository.getOne(id);
        if (saved == null)
            throw new IllegalArgumentException("用户不存在");

        saved.setEmail(user.getEmail());
        saved.setGender(user.getGender());
        saved.setNickName(user.getNickName());
        return saved;
    }

}