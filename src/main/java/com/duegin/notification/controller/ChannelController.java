package com.duegin.notification.controller;

import com.duegin.notification.config.Result;
import com.duegin.notification.domain.dto.ChannelSaveDTO;
import com.duegin.notification.entity.Channel;
import com.duegin.notification.service.ChannelService;
import com.mybatisflex.core.paginate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;


@RestController
@RequestMapping("/api/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    /**
     * 添加 通知通道配置
     *
     * @param channelSaveDTO 通知通道配置
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    public Result<Void> save(@RequestBody ChannelSaveDTO channelSaveDTO) {
        channelService.insertOrUpdate(channelSaveDTO);
        return Result.ok();
    }


    /**
     * 根据主键删除通知通道配置
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Serializable id) {
        return channelService.removeById(id);
    }


    /**
     * 根据通知通道配置主键获取详细信息。
     *
     * @param id notification主键
     * @return 通知通道配置详情
     */
    @GetMapping("/getInfo/{id}")
    public Channel getInfo(@PathVariable Serializable id) {
        return channelService.getById(id);
    }


    /**
     * 分页查询通知通道配置
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Page<Channel> page(Page<Channel> page) {
        return channelService.page(page);
    }
}