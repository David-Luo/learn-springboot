package xin.luowei.demo.springboot.practice.account;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * UserTest
 */
public class UserTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testPass() {
        User user = new User();
        user.setMobile("13580000000");
        user.setPassword("password1");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user,User.OnRegister.class);
        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void test_error_password() {
        User user = new User();
        user.setMobile("13580000000");
        user.setPassword("pa");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user,User.OnRegister.class);
        assertEquals(1, constraintViolations.size());
        assertEquals("密码不能少于6位,至少同时包含数字和字母", constraintViolations.iterator().next().getMessage());
    }


}