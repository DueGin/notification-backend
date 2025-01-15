package com.duegin.notification.controller;

import com.duegin.notification.config.Result;
import com.duegin.notification.domain.vo.channel_auth.ChannelAuthVO;
import com.duegin.notification.service.channel.auth.ChannelAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 频道授权 控制层。
 *
 * @author DueGin
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping("/api/channelAuth")
@Api(tags = "频道授权")
public class ChannelAuthController {

    @Autowired
    private ChannelAuthService channelAuthService;

    @ApiOperation(value = "创建频道授权", notes = "创建频道授权")
    @PostMapping("/create")
    public Result<String> createChannelAuth(@NotNull Integer channelId) {
        return new Result<String>().setData(channelAuthService.createAuth(channelId));
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

    @ApiOperation(value = "根据频道ID获取频道授权列表", notes = "根据频道ID获取频道授权列表")
    @GetMapping("/list")
    public Result<List<ChannelAuthVO>> getList(@NotNull Integer channelId) {
        return Result.ok(channelAuthService.getList(channelId));
    }
}