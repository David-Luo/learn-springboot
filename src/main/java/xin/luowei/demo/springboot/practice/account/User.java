package xin.luowei.demo.springboot.practice.account;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import xin.luowei.demo.springboot.practice.encryption.DESConverter;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    public User(){
        
    }
    /**
     * 主键id 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误", groups = { OnRegister.class, OnUdatePassword.class })
    private String mobile;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^([a-zA-Z]\\w{5,17})$", message = "密码不能少于6位,至少同时包含数字和字母", groups = { OnRegister.class, OnUdatePassword.class })
    @Convert(converter = DESConverter.class)
    private String password;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不对")
    private String email="";

    /**
     * 昵称
     */
    @Length(max = 20, message = "用户名不能超过20个字符", groups = { OnRegister.class })
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String nickName="";

    /**
     * 性别
     */
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.unknow;

    /**
     * 创建时间
     */
    @CreatedDate
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Version
    private LocalDateTime updateTime;

    interface OnRegister {
    }

    interface OnUdatePassword {
    }

    enum Gender {
        unknow, male, female
    }
}