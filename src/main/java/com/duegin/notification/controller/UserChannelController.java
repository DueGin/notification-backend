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
import com.duegin.notification.service.UserChannelService;
import com.duegin.notification.entity.UserChannel;
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
@RequestMapping("/userChannel")
public class UserChannelController {

    @Autowired
    private UserChannelService userChannelService;

    /**
     * 添加 用户订阅通知
     *
     * @param userChannel 用户订阅通知
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    public boolean save(@RequestBody UserChannel userChannel) {
        return userChannelService.save(userChannel);
    }


    /**
     * 根据主键删除用户订阅通知
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return userChannelService.removeById(id);
    }


    /**
     * 根据主键更新用户订阅通知
     *
     * @param userChannel 用户订阅通知
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    public boolean update(@RequestBody UserChannel userChannel) {
        return userChannelService.updateById(userChannel);
    }


    /**
     * 查询所有用户订阅通知
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    public List<UserChannel> list() {
        return userChannelService.list();
    }


    /**
     * 根据用户订阅通知主键获取详细信息。
     *
     * @param id userNotification主键
     * @return 用户订阅通知详情
     */
    @GetMapping("/getInfo/{id}")
    public UserChannel getInfo(@PathVariable Serializable id) {
        return userChannelService.getById(id);
    }


    /**
     * 分页查询用户订阅通知
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Page<UserChannel> page(Page<UserChannel> page) {
        return userChannelService.page(page);
    }
}