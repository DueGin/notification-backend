package com.duegin.notification.domain.dto.user;

import lombok.Data;

/**
 * 用户保存DTO
 *
 * @author DueGin
 * @date 2025/1/19
 */
@Data
public class UserSaveDTO {

    private Integer id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

}
