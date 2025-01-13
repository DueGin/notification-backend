package com.duegin.notification.service;


import com.duegin.notification.config.base.service.impl.BaseServiceImpl;
import com.duegin.notification.convert.ChannelConvertor;
import com.duegin.notification.domain.dto.ChannelSaveDTO;
import com.duegin.notification.entity.Channel;
import com.duegin.notification.mapper.ChannelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 通知通道配置 服务层实现。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Slf4j
@Service
public class ChannelService extends BaseServiceImpl<ChannelMapper, Channel> {

    @Autowired
    private ChannelConvertor channelConvertor;

    public void insertOrUpdate(ChannelSaveDTO channelSaveDTO) {
        Channel channel = channelConvertor.channelSaveDTOToChannel(channelSaveDTO);

        channel.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
        mapper.insertOrUpdate(channel);
    }

}