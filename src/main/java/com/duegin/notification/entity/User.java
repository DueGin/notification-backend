package com.duegin.notification.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 实体类。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Table(value = "user")
@Data
public class User {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * UID
     */
    @Column(value = "account")
    private String account;

    /**
     * 用户名
     */
    @Column(value = "username")
    private String username;

    /**
     * 密码
     */
    @Column(value = "password")
    private String password;

    /**
     * 邮箱
     */
    @Column(value = "email")
    private String email;

    @Column(value = "create_time")
    private LocalDateTime createTime;

    @Column(value = "update_time")
    private LocalDateTime updateTime;

    @Column(value = "deleted")
    private Integer deleted;

}
