package com.duegin.notification.controller;

import com.duegin.notification.config.Result;
import com.duegin.notification.domain.vo.channel.auth.ChannelAuthVO;
import com.duegin.notification.service.channel.auth.ChannelAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@Validated
@RestController
@RequestMapping("/api/channel/auth")
public class ChannelAuthController {

    @Autowired
    private ChannelAuthService channelAuthService;

    /**
     * 创建频道授权
     */
    @PostMapping("/create")
    public Result<String> createChannelAuth(@NotNull Integer channelId) {
        return new Result<String>().setData(channelAuthService.createAuth(channelId));
    }

    /**
     * 根据主键删除频道授权
     */
    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Integer id) {
        return channelAuthService.removeById(id);
    }

    /**
     * 查询当前用户指定频道的授权列表
     */
    @GetMapping("/list")
    public Result<List<ChannelAuthVO>> getList(@NotNull Integer channelId) {
        return Result.ok(channelAuthService.getList(channelId));
    }
}