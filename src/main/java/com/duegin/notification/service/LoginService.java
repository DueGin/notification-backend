package com.duegin.notification.service;

import com.duegin.notification.config.exception.BusinessException;
import com.duegin.notification.domain.dto.LoginDTO;
import com.duegin.notification.domain.po.User;
import com.duegin.notification.mapper.UserMapper;
import com.mybatisflex.core.query.QueryChain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.duegin.notification.domain.po.table.UserTableDef.USER;

/**
 * @author DueGin
 * @date 2025/1/2
 */
@Slf4j
@Service
public class LoginService {

    @Resource
    private UserMapper userMapper;


    public void login(LoginDTO loginDTO) {
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
    }
}
