package com.duegin.notification.service.channel;

import com.duegin.notification.domain.dto.ChannelAcceptDTO;
import com.duegin.notification.entity.Channel;
import com.duegin.notification.mapper.ChannelMapper;
import com.mybatisflex.core.query.QueryChain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.duegin.notification.entity.table.ChannelTableDef.CHANNEL;

/**
 * @author DueGin
 * @date 2025/1/4
 */
@Slf4j
@Service
public class ChannelAcceptService {

    @Autowired
    private ChannelMapper channelMapper;

    public void accept(ChannelAcceptDTO acceptDTO) {
        String name = acceptDTO.getName();
        String callbackUrl = acceptDTO.getCallbackUrl();
        Channel channel = QueryChain.of(channelMapper)
                .from(CHANNEL)
                .where(CHANNEL.NAME.eq(name))
                .one();

        if (channel == null) {
            // 是否需要请求callbackUrl
            if (StringUtils.isBlank(callbackUrl)) {
                log.warn("通知频道不存在 ==> name: {}, callbackUrl: {}", name, callbackUrl);
                return;
            }

        }


    }
}
