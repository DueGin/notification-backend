package com.duegin.notification.convert;

import com.duegin.notification.domain.dto.channel.user.ChannelUserPageDTO;
import com.duegin.notification.domain.dto.channel.user.ChannelUserSaveDTO;
import com.duegin.notification.domain.dto.channel.user.subscribe.ChannelUserSubscribePageDTO;
import com.duegin.notification.entity.ChannelUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author DueGin
 * @date 2025/1/13
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChannelUserConvertor {

    ChannelUser channelUserSaveDTO2ChannelUser(ChannelUserSaveDTO channelUserSaveDTO);

    ChannelUserPageDTO channelUserSubscribePageDTO2ChannelUserPageDTO(ChannelUserSubscribePageDTO channelUserSubscribePageDTO);
}
