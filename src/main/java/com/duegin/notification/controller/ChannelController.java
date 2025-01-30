package com.duegin.notification.controller;

import com.duegin.notification.config.Result;
import com.duegin.notification.domain.dto.ChannelSaveDTO;
import com.duegin.notification.domain.dto.channel.ChannelPageDTO;
import com.duegin.notification.domain.vo.channel.ChannelVO;
import com.duegin.notification.service.ChannelService;
import com.mybatisflex.core.paginate.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result<Void> save(@RequestBody ChannelSaveDTO channelSaveDTO) {
        channelService.insertOrUpdate(channelSaveDTO);
        return Result.ok();
    }


    /**
     * 删除
     */
    @DeleteMapping("/remove/{id}")
    public Result<Void> remove(@PathVariable Integer id) {
        channelService.removeById(id);
        return Result.ok();
    }


    /**
     * 详情
     */
    @GetMapping("/getInfo/{id}")
    public Result<ChannelVO> getInfo(@PathVariable Integer id) {
        return Result.ok(channelService.getInfo(id));
    }


    /**
     * 获取分页
     */
    @GetMapping("/page")
    public Result<Page<ChannelVO>> page(Page page, ChannelPageDTO channelPageDTO) {
        return Result.ok(channelService.getPage(page, channelPageDTO));
    }
}