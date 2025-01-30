package com.duegin.notification.controller;

import com.duegin.notification.config.Result;
import com.duegin.notification.domain.dto.channel.user.subscribe.ChannelUserSubscribePageDTO;
import com.duegin.notification.domain.vo.channel.user.ChannelUserVO;
import com.duegin.notification.service.channel.user.subscribe.ChannelUserSubscribeService;
import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author DueGin
 * @date 2025/1/30
 */
@RestController
@RequestMapping("/api/channel/user/subscribe")
public class ChannelUserSubscribeController {

    @Resource
    private ChannelUserSubscribeService channelUserSubscribeService;


    /**
     * 获取用户自己订阅列表
     */
    @GetMapping("/list")
    public Result<Page<ChannelUserVO>> getSelfSubscribeList(Page page, ChannelUserSubscribePageDTO channelUserSubscribePageDTO) {
        return Result.ok(channelUserSubscribeService.getSelfSubscribePage(page, channelUserSubscribePageDTO));
    }

    /**
     * 删除用户自己订阅
     */
    @DeleteMapping("/{id}")
    public Result<Void> removeSelfSubscribe(@PathVariable Integer id) {
        channelUserSubscribeService.removeSelfSubscribe(id);
        return Result.ok();
    }
}
