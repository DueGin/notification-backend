package com.duegin.notification.domain.vo.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author DueGin
 * @date 2025/1/18
 */
@Data
public class UserVO {

    private Integer id;

    /**
     * UID
     */
    private String account;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
