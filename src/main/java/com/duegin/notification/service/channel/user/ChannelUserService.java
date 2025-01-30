package com.duegin.notification.service.channel.user;


import com.duegin.notification.config.base.service.impl.BaseServiceImpl;
import com.duegin.notification.convert.ChannelUserConvertor;
import com.duegin.notification.domain.dto.channel.user.ChannelUserPageDTO;
import com.duegin.notification.domain.dto.channel.user.ChannelUserSaveDTO;
import com.duegin.notification.domain.vo.channel.user.ChannelUserVO;
import com.duegin.notification.entity.ChannelUser;
import com.duegin.notification.mapper.ChannelUserMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.duegin.notification.entity.table.ChannelTableDef.CHANNEL;
import static com.duegin.notification.entity.table.ChannelUserTableDef.CHANNEL_USER;
import static com.duegin.notification.entity.table.UserTableDef.USER;

/**
 * 用户订阅通知服务
 */
@Service
@AllArgsConstructor
public class ChannelUserService extends BaseServiceImpl<ChannelUserMapper, ChannelUser> {

    private final ChannelUserConvertor channelUserConvertor;

    public Page<ChannelUserVO> getPage(Page<ChannelUserVO> page, ChannelUserPageDTO channelUserPageDTO) {
        return queryChain()
                .select(CHANNEL.NAME.as("channelName"))
                .select((CHANNEL_USER.ALL_COLUMNS))
                .select(USER.USERNAME.as("username"))
                .leftJoin(CHANNEL).on(CHANNEL.ID.eq(CHANNEL_USER.CHANNEL_ID))
                .leftJoin(USER).on(CHANNEL_USER.USER_ID.eq(USER.ID))
                .where(CHANNEL.UUID.like(channelUserPageDTO.getUuid(), If::hasText))
                .and(CHANNEL.ID.eq(channelUserPageDTO.getChannelId(), If::notNull))
                .and(CHANNEL_USER.USER_ID.eq(channelUserPageDTO.getUserId(), If::notNull))
                .and(CHANNEL_USER.CREATE_TIME.between(channelUserPageDTO.getCreateTimeFrom() + " 00:00:00", channelUserPageDTO.getCreateTimeTo() + " 23:59:59",
                        channelUserPageDTO.getCreateTimeFrom() != null && channelUserPageDTO.getCreateTimeTo() != null))
                .pageAs(page, ChannelUserVO.class);
    }

    public Integer save(ChannelUserSaveDTO channelUserSaveDTO) {
        ChannelUser channelUser = channelUserConvertor.channelUserSaveDTO2ChannelUser(channelUserSaveDTO);
        mapper.insert(channelUser);
        return channelUser.getId();
    }
}