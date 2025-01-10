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
import com.duegin.notification.service.ChannelAuthService;
import com.duegin.notification.entity.ChannelAuth;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;

/**
 * 频道授权 控制层。
 *
 * @author DueGin
 * @since 1.0
 */
@RestController
@RequestMapping("/channelAuth")
@Api(tags = "频道授权")
public class ChannelAuthController {

    @Autowired
    private ChannelAuthService channelAuthService;

    /**
     * 添加 频道授权
     *
     * @param channelAuth 频道授权
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加频道授权", notes = "添加频道授权")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = ""),
            @ApiImplicitParam(name = "channelId", value = "订阅通知ID"),
            @ApiImplicitParam(name = "privateKey", value = "私钥"),
            @ApiImplicitParam(name = "publicKey", value = "公钥"),
            @ApiImplicitParam(name = "createTime", value = "创建时间"),
            @ApiImplicitParam(name = "deleted", value = "")
    })
    public boolean save(@RequestBody ChannelAuth channelAuth) {
        return channelAuthService.save(channelAuth);
    }


    /**
     * 根据主键删除频道授权
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "根据主键删除频道授权", notes = "根据主键删除频道授权")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", required = true)
    })
    public boolean remove(@PathVariable Serializable id) {
        return channelAuthService.removeById(id);
    }


    /**
     * 根据主键更新频道授权
     *
     * @param channelAuth 频道授权
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @ApiOperation(value = "根据主键更新频道授权", notes = "根据主键更新频道授权")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", required = true),
            @ApiImplicitParam(name = "channelId", value = "订阅通知ID"),
            @ApiImplicitParam(name = "privateKey", value = "私钥"),
            @ApiImplicitParam(name = "publicKey", value = "公钥"),
            @ApiImplicitParam(name = "createTime", value = "创建时间"),
            @ApiImplicitParam(name = "deleted", value = "")
    })
    public boolean update(@RequestBody ChannelAuth channelAuth) {
        return channelAuthService.updateById(channelAuth);
    }


    /**
     * 查询所有频道授权
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有频道授权", notes = "查询所有频道授权")
    public List<ChannelAuth> list() {
        return channelAuthService.list();
    }


    /**
     * 根据频道授权主键获取详细信息。
     *
     * @param id channelAuth主键
     * @return 频道授权详情
     */
    @GetMapping("/getInfo/{id}")
    @ApiOperation(value = "根据频道授权主键获取详细信息", notes = "根据频道授权主键获取详细信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "", required = true)
    })
    public ChannelAuth getInfo(@PathVariable Serializable id) {
        return channelAuthService.getById(id);
    }


    /**
     * 分页查询频道授权
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询频道授权", notes = "分页查询频道授权")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageNumber", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页大小", required = true)
    })
    public Page<ChannelAuth> page(Page<ChannelAuth> page) {
        return channelAuthService.page(page);
    }
}