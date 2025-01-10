package com.duegin.notification.service;

import com.duegin.notification.config.exception.BusinessException;
import com.duegin.notification.domain.dto.LoginDTO;
import com.duegin.notification.entity.User;
import com.duegin.notification.mapper.UserMapper;
import com.duegin.notification.utils.JwtTokenUtils;
import com.mybatisflex.core.query.QueryChain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.Collections;

import static com.duegin.notification.entity.table.UserTableDef.USER;


/**
 * @author DueGin
 * @date 2025/1/2
 */
@Slf4j
@Service
public class LoginService {

    @Resource
    private UserMapper userMapper;

    public String login(LoginDTO loginDTO, HttpServletResponse response) {
        String password = loginDTO.getPassword();
        DigestUtils.sha1Hex(password);
        User user = QueryChain.of(userMapper)
                .from(USER)
                .where(USER.USERNAME.eq(loginDTO.getUsername()))
                .and(USER.PASSWORD.eq(password))
                .limit(1)
                .one();

        if (user == null) {
            throw new BusinessException("账号或密码错误");
        }

        // 发token
        try {
            return JwtTokenUtils.createToken(String.valueOf(user.getId()), Collections.emptyList(), Collections.emptyMap(), loginDTO.getIsRememberMe());
        } catch (Exception e) {
            throw new BusinessException("登录失败，请联系管理员！");
        }
    }
}
