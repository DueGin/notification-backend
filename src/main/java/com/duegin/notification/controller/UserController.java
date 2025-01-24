package com.duegin.notification.controller;

import com.duegin.notification.config.Result;
import com.duegin.notification.config.UserContext;
import com.duegin.notification.domain.dto.user.LoginDTO;
import com.duegin.notification.domain.dto.user.RegisterDTO;
import com.duegin.notification.domain.dto.user.UserPageDTO;
import com.duegin.notification.domain.dto.user.UserSaveDTO;
import com.duegin.notification.domain.vo.user.UserVO;
import com.duegin.notification.entity.User;
import com.duegin.notification.mapper.UserMapper;
import com.duegin.notification.service.LoginService;
import com.duegin.notification.service.UserService;
import com.duegin.notification.utils.JwtTokenUtils;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

import static com.duegin.notification.entity.table.UserTableDef.USER;


@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Resource
    private LoginService loginService;
    @Resource
    private UserMapper userMapper;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        String token = loginService.login(loginDTO, response);
        // 设置token头浏览器可见
        response.setHeader("Access-Control-Expose-Headers", JwtTokenUtils.TOKEN_HEADER);

        // 设置响应头
        response.setHeader(JwtTokenUtils.TOKEN_HEADER, JwtTokenUtils.TOKEN_PREFIX + token);
        log.info("登录成功，userId: {}", UserContext.getUserId());
        return currentUser();
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody RegisterDTO registerDTO) {
        loginService.register(registerDTO);
        return Result.ok();
    }

    /**
     * 获取当前登录用户信息
     */
    @GetMapping("/currentUser")
    public Result<UserVO> currentUser() {
        Integer userId = UserContext.getUserId();
        UserVO userVO = QueryChain.of(userMapper)
                .from(USER)
                .where(USER.ID.eq(userId))
                .oneAs(UserVO.class);
        return Result.ok(userVO);
    }

    /**
     * 保存用户
     */
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody UserSaveDTO userSaveDTO) {
        return Result.ok(userService.insertOrUpdateUser(userSaveDTO));
    }


    /**
     * 删除用户
     */
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return userService.removeById(id);
    }


    /**
     * 查询所有
     */
    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }


    /**
     * 用户详情
     */
    @GetMapping("/{id}")
    public Result<UserVO> getInfo(@PathVariable Integer id) {
        return Result.ok(QueryChain.of(userMapper)
                .from(USER)
                .where(USER.ID.eq(id))
                .oneAs(UserVO.class));
    }


    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result<Page<UserVO>> page(Page page, UserPageDTO userPageDTO) {
        return Result.ok(userService.getPage(page, userPageDTO));
    }
}