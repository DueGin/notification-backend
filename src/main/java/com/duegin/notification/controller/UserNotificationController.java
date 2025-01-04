package com.duegin.notification.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.duegin.notification.service.UserNotificationService;
import com.duegin.notification.domain.po.UserNotification;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * 用户订阅通知 控制层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@RestController
@RequestMapping("/userNotification")
public class UserNotificationController {

    @Autowired
    private UserNotificationService userNotificationService;

    /**
     * 添加 用户订阅通知
     *
     * @param userNotification 用户订阅通知
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    public boolean save(@RequestBody UserNotification userNotification) {
        return userNotificationService.save(userNotification);
    }


    /**
     * 根据主键删除用户订阅通知
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return userNotificationService.removeById(id);
    }


    /**
     * 根据主键更新用户订阅通知
     *
     * @param userNotification 用户订阅通知
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    public boolean update(@RequestBody UserNotification userNotification) {
        return userNotificationService.updateById(userNotification);
    }


    /**
     * 查询所有用户订阅通知
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    public List<UserNotification> list() {
        return userNotificationService.list();
    }


    /**
     * 根据用户订阅通知主键获取详细信息。
     *
     * @param id userNotification主键
     * @return 用户订阅通知详情
     */
    @GetMapping("/getInfo/{id}")
    public UserNotification getInfo(@PathVariable Serializable id) {
        return userNotificationService.getById(id);
    }


    /**
     * 分页查询用户订阅通知
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Page<UserNotification> page(Page<UserNotification> page) {
        return userNotificationService.page(page);
    }
}