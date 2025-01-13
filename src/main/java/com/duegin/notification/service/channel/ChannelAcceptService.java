package com.duegin.notification.service.channel;

import com.duegin.notification.domain.dto.ChannelAcceptDTO;
import com.duegin.notification.entity.Channel;
import com.duegin.notification.entity.ChannelMessage;
import com.duegin.notification.entity.User;
import com.duegin.notification.mapper.ChannelMapper;
import com.duegin.notification.mapper.ChannelMessageMapper;
import com.duegin.notification.service.channel.auth.ChannelAuthService;
import com.duegin.notification.utils.IpUtil;
import com.mybatisflex.core.query.QueryChain;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    @Autowired
    private ChannelAuthService channelAuthService;
    @Autowired
    private ChannelMessageMapper channelMessageMapper;

    public void accept(ChannelAcceptDTO acceptDTO) {
        String hostIp = IpUtil.getHostIp();
        String name = acceptDTO.getName();
        String callbackUrl = acceptDTO.getCallbackUrl();
        log.info("接受到消息 ==> hostIp: {}, name: {}, callbackUrl: {}", hostIp, name, callbackUrl);

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
            return;
        }

        // 校验token
        User user = channelAuthService.checkAccount(acceptDTO.getToken());

        if (user == null) {
            log.warn("用户不存在 ==> token: {}", acceptDTO.getToken());
            return;
        }


        ChannelMessage channelMessage = new ChannelMessage()
                .setChannelId(channel.getId())
                .setMessage(acceptDTO.getText())
                .setAcceptTime(LocalDateTime.now())
                .setIp(hostIp);

        channelMessageMapper.insert(channelMessage);

        // TODO 发送至对应通道
    }
}
