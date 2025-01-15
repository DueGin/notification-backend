package com.duegin.notification.controller;

import com.duegin.notification.config.Result;
import com.duegin.notification.domain.dto.user.LoginDTO;
import com.duegin.notification.domain.dto.user.RegisterDTO;
import com.duegin.notification.entity.User;
import com.duegin.notification.service.LoginService;
import com.duegin.notification.service.UserService;
import com.duegin.notification.utils.JwtTokenUtils;
import com.mybatisflex.core.paginate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;

/**
 * 用户控制层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public Result<Void> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        String token = loginService.login(loginDTO, response);
        // 设置token头浏览器可见
        response.setHeader("Access-Control-Expose-Headers", JwtTokenUtils.TOKEN_HEADER);

        // 设置响应头
        response.setHeader(JwtTokenUtils.TOKEN_HEADER, JwtTokenUtils.TOKEN_PREFIX + token);
        return Result.ok();
    }

    @PostMapping("/register")
    public Result<Void> register(@RequestBody RegisterDTO registerDTO) {
        loginService.register(registerDTO);
        return Result.ok();
    }

    /**
     * 添加
     *
     * @param user
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }


    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return userService.removeById(id);
    }


    /**
     * 根据主键更新
     *
     * @param user
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    public boolean update(@RequestBody User user) {
        return userService.updateById(user);
    }


    /**
     * 查询所有
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id user主键
     * @return 详情
     */
    @GetMapping("/getInfo/{id}")
    public User getInfo(@PathVariable Serializable id) {
        return userService.getById(id);
    }


    /**
     * 分页查询
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Page<User> page(Page<User> page) {
        return userService.page(page);
    }
}