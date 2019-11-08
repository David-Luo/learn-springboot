package xin.luowei.demo.springboot.practice.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import xin.luowei.demo.springboot.practice.account.User;
import xin.luowei.demo.springboot.practice.account.User.UserChangedEvent;

/**
 * UserEventHandler
 */
@Component
@Slf4j
public class UserEventHandler {

    @EventListener
    public void process(UserChangedEvent event) throws Exception {
        User user = event.getUser();
        log.info("listen event:"+user);

    }
}