package com.mall.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hcq
 * @since 2019-04-16
 */
@Data
@TableName("mall_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private  Long id;

    /**
     * 用户名
     */
    @Length(min = 4, max = 30, message = "用户名只能在4~30位之间")
    private String username;

    /**
     * 密码，加密存储
     */
    @Length(min = 4, max = 30, message = "密码只能在4~30位之间")
    private String password;

    /**
     * 注册手机号
     */
    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime created;


}
