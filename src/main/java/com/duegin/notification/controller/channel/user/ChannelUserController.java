package com.duegin.notification.controller.channel.user;

import com.duegin.notification.config.Result;
import com.duegin.notification.domain.dto.channel.user.ChannelUserPageDTO;
import com.duegin.notification.domain.dto.channel.user.ChannelUserSaveDTO;
import com.duegin.notification.domain.vo.channel.user.ChannelUserVO;
import com.duegin.notification.service.channel.user.ChannelUserService;
import com.mybatisflex.core.paginate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/channel/user")
public class ChannelUserController {

    @Autowired
    private ChannelUserService channelUserService;

    /**
     * 添加用户订阅通知
     */
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody ChannelUserSaveDTO channelUserSaveDTO) {
        return Result.ok(channelUserService.save(channelUserSaveDTO));
    }


    /**
     * 删除用户订阅
     */
    @DeleteMapping("/remove/{id}")
    public Result<Void> remove(@PathVariable Integer id) {
        channelUserService.removeById(id);
        return Result.ok();
    }

    /**
     * 分页
     */
    @GetMapping("/page")
    public Result<Page<ChannelUserVO>> getPage(Page page, ChannelUserPageDTO channelUserPageDTO) {
        return Result.ok(channelUserService.getPage(page, channelUserPageDTO));
    }


}