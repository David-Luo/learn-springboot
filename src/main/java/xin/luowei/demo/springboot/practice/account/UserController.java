package xin.luowei.demo.springboot.practice.account;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class UserController {

    @Autowired
    private RegistorService registorService;
    @Autowired
    private UserService  userService;

    @GetMapping("/user/{userId}")
    public User getUser(@Max(10) @PathVariable Integer userId){
        if(userId == null){
            return null;
        }
        return userService.getUser(userId);
    }
    
    @PutMapping("/user/{userId}")
    public User updateUserInfo(@PathVariable Integer userId, @Validated(User.OnRegister.class) @RequestBody User user){
        user.setId(userId);
        return userService.getUser(userId);
    }
    
    @PostMapping("/user")
    public Integer registor(@Validated(User.OnRegister.class) @RequestBody User user) {
        registorService.registor(user);
        return user.getId();
    }


}