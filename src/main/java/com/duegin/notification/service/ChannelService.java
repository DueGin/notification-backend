package com.duegin.notification.service;


import com.duegin.notification.config.UserContext;
import com.duegin.notification.config.base.service.impl.BaseServiceImpl;
import com.duegin.notification.convert.ChannelConvertor;
import com.duegin.notification.domain.dto.ChannelSaveDTO;
import com.duegin.notification.domain.dto.channel.ChannelPageDTO;
import com.duegin.notification.domain.vo.channel.ChannelVO;
import com.duegin.notification.entity.Channel;
import com.duegin.notification.mapper.ChannelMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.duegin.notification.entity.table.ChannelTableDef.CHANNEL;
import static com.duegin.notification.entity.table.UserTableDef.USER;

/**
 * 通知通道配置服务
 */
@Slf4j
@Service
public class ChannelService extends BaseServiceImpl<ChannelMapper, Channel> {

    @Autowired
    private ChannelConvertor channelConvertor;

    public void insertOrUpdate(ChannelSaveDTO channelSaveDTO) {
        Channel channel = channelConvertor.channelSaveDTOToChannel(channelSaveDTO);
        Integer userId = UserContext.getUserId();
        if (channel.getId() == null || channel.getId() == 0) {
            channel.setUuid(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
            channel.setCreateUser(userId);
        }
        mapper.insertOrUpdateSelective(channel);
    }

    public Page<ChannelVO> getPage(Page<ChannelVO> page, ChannelPageDTO channelPageDTO) {
        return queryChain()
                .select(CHANNEL.ALL_COLUMNS)
                .select(USER.USERNAME.as("createUsername"))
                .leftJoin(USER).on(CHANNEL.CREATE_USER.eq(USER.ID))
                .where(CHANNEL.UUID.eq(channelPageDTO.getUuid(), If::hasText))
                .and(CHANNEL.NAME.eq(channelPageDTO.getName(), If::hasText))
                .pageAs(page, ChannelVO.class);
    }

    public ChannelVO getInfo(Integer id) {
        return queryChain()
                .select(CHANNEL.ALL_COLUMNS)
                .select(USER.USERNAME.as("createUsername"))
                .leftJoin(USER).on(CHANNEL.CREATE_USER.eq(USER.ID))
                .where(CHANNEL.ID.eq(id))
                .oneAs(ChannelVO.class);
    }

}