package com.duegin.notification.config.filter;


import com.duegin.notification.config.UserContext;
import com.duegin.notification.config.exception.BusinessException;
import com.duegin.notification.config.properties.SecurityProperties;
import com.duegin.notification.entity.User;
import com.duegin.notification.mapper.UserMapper;
import com.duegin.notification.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理HTTP请求的BASIC授权标头，然后将结果放入SecurityContextHolder
 *
 * @author crush
 */
@Component
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
@EnableConfigurationProperties(SecurityProperties.class)
public class JwtAuthorizationFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    /**
     * security properties
     * 在配置文件中配置
     */
    @Resource
    private SecurityProperties securityProperties;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 过滤白名单路由
        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String exclude : securityProperties.getExcludes()) {
            if (antPathMatcher.match(exclude, uri)) {
                chain.doFilter(request, response);
                return;
            }
        }

        // 拿出请求头的token
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            response.setStatus(403);
            response.getWriter().write("NO LOGIN");
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
        UserContext.setUser(user);
    }

}
