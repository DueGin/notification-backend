package com.duegin.notification.domain.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 登录DTO
 *
 * @author DueGin
 * @date 2025/1/2
 */
@Data
public class LoginDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    private Boolean isRememberMe = false;
}
