package com.duegin.notification.convert;

import com.duegin.notification.domain.dto.ChannelSaveDTO;
import com.duegin.notification.entity.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author DueGin
 * @date 2025/1/13
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChannelConvertor {

    Channel channelSaveDTOToChannel(ChannelSaveDTO channelSaveDTO);
}
