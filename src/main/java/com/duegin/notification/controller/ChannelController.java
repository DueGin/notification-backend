package com.duegin.notification.controller;

import com.duegin.notification.config.Result;
import com.duegin.notification.domain.dto.ChannelSaveDTO;
import com.duegin.notification.entity.Channel;
import com.duegin.notification.service.ChannelService;
import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;

/**
 * 通知通道配置 控制层。
 *
 * @author DueGin
 * @since 1.0
 */
@RestController
@RequestMapping("/channel")
@Api(tags = "通知通道配置")
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
    @ApiOperation(value = "添加通知通道配置", notes = "添加通知通道配置")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = ""),
            @ApiImplicitParam(name = "name", value = "订阅通知名称"),
            @ApiImplicitParam(name = "createUser", value = "创建人"),
            @ApiImplicitParam(name = "createTime", value = ""),
            @ApiImplicitParam(name = "updateTime", value = "")
    })
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
    @ApiOperation(value = "根据主键删除通知通道配置", notes = "根据主键删除通知通道配置")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", required = true)
    })
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
    @ApiOperation(value = "根据通知通道配置主键获取详细信息", notes = "根据通知通道配置主键获取详细信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", required = true)
    })
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
    @ApiOperation(value = "分页查询通知通道配置", notes = "分页查询通知通道配置")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNumber", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true)
    })
    public Page<Channel> page(Page<Channel> page) {
        return channelService.page(page);
    }
}