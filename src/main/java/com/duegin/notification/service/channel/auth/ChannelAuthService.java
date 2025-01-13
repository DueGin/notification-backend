package com.duegin.notification.service.channel.auth;

import com.duegin.notification.config.UserContext;
import com.duegin.notification.config.base.service.impl.BaseServiceImpl;
import com.duegin.notification.domain.vo.channel_auth.ChannelAuthVO;
import com.duegin.notification.entity.ChannelAuth;
import com.duegin.notification.entity.User;
import com.duegin.notification.mapper.ChannelAuthMapper;
import com.duegin.notification.mapper.UserMapper;
import com.duegin.notification.utils.JwtTokenUtils;
import com.mybatisflex.core.query.QueryChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.duegin.notification.entity.table.ChannelAuthTableDef.CHANNEL_AUTH;

/**
 * @author DueGin
 * @date 2025/1/10
 */
@Slf4j
@Service
public class ChannelAuthService extends BaseServiceImpl<ChannelAuthMapper, ChannelAuth> {
    @Resource
    private UserMapper userMapper;


    public String createAuth(Integer channelId) {
        Integer userId = UserContext.getUserId();
        try {
            String channelAuthKey = JwtTokenUtils.createChannelAuthToken(userId);

            ChannelAuth channelAuth = new ChannelAuth()
                    .setUserId(userId)
                    .setChannelId(channelId)
                    .setToken(channelAuthKey);

            mapper.insert(channelAuth);

            return channelAuthKey;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public User checkAccount(String token) {

        ChannelAuth channelAuth = QueryChain.of(mapper)
                .from(CHANNEL_AUTH)
                .where(CHANNEL_AUTH.TOKEN.eq(token))
                .limit(1)
                .one();

        if (channelAuth == null) {
            return null;
        }

        boolean b = JwtTokenUtils.checkChannelAuthToken(token);
        if (!b) {
            log.warn("token已失效 ==> token: {}", token);
            return null;
        }

        Integer userId = Integer.parseInt(JwtTokenUtils.getUserId(token));

        return userMapper.selectOneById(userId);
    }

    public List<ChannelAuthVO> getList(Integer channelId) {
        if (channelId == null) {
            return Collections.emptyList();
        }
        Integer userId = UserContext.getUserId();

        return QueryChain.of(mapper)
                .from(CHANNEL_AUTH)
                .where(CHANNEL_AUTH.USER_ID.eq(userId))
                .and(CHANNEL_AUTH.CHANNEL_ID.eq(channelId))
                .and(CHANNEL_AUTH.DELETED.eq(0))
                .listAs(ChannelAuthVO.class);
    }
}
