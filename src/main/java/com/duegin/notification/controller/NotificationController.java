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
import com.duegin.notification.service.NotificationService;
import com.duegin.notification.domain.po.Notification;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * 通知通道配置 控制层。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 添加 通知通道配置
     *
     * @param notification 通知通道配置
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    public boolean save(@RequestBody Notification notification) {
        return notificationService.save(notification);
    }


    /**
     * 根据主键删除通知通道配置
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return notificationService.removeById(id);
    }


    /**
     * 根据主键更新通知通道配置
     *
     * @param notification 通知通道配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    public boolean update(@RequestBody Notification notification) {
        return notificationService.updateById(notification);
    }


    /**
     * 查询所有通知通道配置
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    public List<Notification> list() {
        return notificationService.list();
    }


    /**
     * 根据通知通道配置主键获取详细信息。
     *
     * @param id notification主键
     * @return 通知通道配置详情
     */
    @GetMapping("/getInfo/{id}")
    public Notification getInfo(@PathVariable Serializable id) {
        return notificationService.getById(id);
    }


    /**
     * 分页查询通知通道配置
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Page<Notification> page(Page<Notification> page) {
        return notificationService.page(page);
    }
}