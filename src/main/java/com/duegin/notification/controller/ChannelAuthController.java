package com.duegin.notification.controller;

import com.duegin.notification.config.Result;
import com.duegin.notification.domain.vo.channel_auth.ChannelAuthVO;
import com.duegin.notification.service.channel.auth.ChannelAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Validated
@RestController
@RequestMapping("/api/channelAuth")
public class ChannelAuthController {

    @Autowired
    private ChannelAuthService channelAuthService;

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
    public boolean remove(@PathVariable Serializable id) {
        return channelAuthService.removeById(id);
    }

    @GetMapping("/list")
    public Result<List<ChannelAuthVO>> getList(@NotNull Integer channelId) {
        return Result.ok(channelAuthService.getList(channelId));
    }
}