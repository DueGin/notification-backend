package com.duegin.notification.config.filter;


import com.duegin.notification.config.BaseContext;
import com.duegin.notification.config.exception.BusinessException;
import com.duegin.notification.entity.User;
import com.duegin.notification.mapper.UserMapper;
import com.duegin.notification.utils.JwtTokenUtils;
import com.mybatisflex.core.query.QueryChain;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.duegin.notification.entity.table.UserTableDef.USER;

/**
 * 处理HTTP请求的BASIC授权标头，然后将结果放入SecurityContextHolder
 *
 * @author crush
 */
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class JwtAuthorizationFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    @Value("jwt.excludes")
    private String[] excludes;

    private UserMapper userMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 过滤白名单路由
        String uri = request.getRequestURI();
        for (String exclude : excludes) {
            if (uri.equals("/api" + exclude)) {
                chain.doFilter(request, response);
                return;
            }
        }

        // 拿出请求头的token
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = JwtTokenUtils.getToken(tokenHeader);

//        // token是否在黑名单中 或 已经退出登录的token
//        boolean had = this.tokenRedis.hasBlankToken(token);
//        if (had) {
//            log.error("token在黑名单中 ==> token: {}", token);
//            chain.doFilter(request, response);
//            return;
//        }

        try {
            // 解析token，填充TheadLocal
            checkAndSetBaseContext(token);
        } catch (ExpiredJwtException e) {
            log.info("token过期 ==> token: {}, msg: {}", token, e.getMessage());
            e.printStackTrace();
        }

        chain.doFilter(request, response);
    }

    private void checkAndSetBaseContext(String token) {
        // 从jwt token中拿出username、角色，然后解析出权限
        Long userId = Long.valueOf(JwtTokenUtils.getUserId(token));
        User user = userMapper.selectOneById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        BaseContext.setCurrentId(user);
    }

}
