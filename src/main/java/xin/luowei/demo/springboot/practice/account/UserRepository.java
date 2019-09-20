package xin.luowei.demo.springboot.practice.account;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User,Integer>{

    
    
}