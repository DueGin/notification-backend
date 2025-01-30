package com.duegin.notification.service.channel.user.subscribe;

import com.duegin.notification.config.UserContext;
import com.duegin.notification.config.exception.BusinessException;
import com.duegin.notification.convert.ChannelUserConvertor;
import com.duegin.notification.domain.dto.channel.user.ChannelUserPageDTO;
import com.duegin.notification.domain.dto.channel.user.subscribe.ChannelUserSubscribePageDTO;
import com.duegin.notification.domain.vo.channel.user.ChannelUserVO;
import com.duegin.notification.entity.ChannelUser;
import com.duegin.notification.mapper.ChannelUserMapper;
import com.duegin.notification.service.channel.user.ChannelUserService;
import com.mybatisflex.core.paginate.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户订阅频道服务
 *
 * @author DueGin
 * @date 2025/1/30
 */
@Slf4j
@Service
public class ChannelUserSubscribeService {

    @Resource
    private ChannelUserService channelUserService;
    @Resource
    private ChannelUserMapper channelUserMapper;
    @Resource
    private ChannelUserConvertor channelUserConvertor;

    /**
     * 获取自己订阅的频道分页列表
     */
    public Page<ChannelUserVO> getSelfSubscribePage(Page<ChannelUserVO> page, ChannelUserSubscribePageDTO channelUserSubscribePageDTO) {
        Integer userId = UserContext.getUserId();
        channelUserSubscribePageDTO.setUserId(userId);
        ChannelUserPageDTO channelUserPageDTO = channelUserConvertor.channelUserSubscribePageDTO2ChannelUserPageDTO(channelUserSubscribePageDTO);
        return channelUserService.getPage(page, channelUserPageDTO);
    }

    /**
     * 删除自己的订阅频道
     */
    public void removeSelfSubscribe(Integer id) {
        ChannelUser channelUser = channelUserMapper.selectOneById(id);
        if (channelUser == null) {
            return;
        }

        Integer userId = UserContext.getUserId();
        if (!userId.equals(channelUser.getUserId())) {
            throw new BusinessException("不能取消关注别人订阅的频道哦！");
        }

        channelUserMapper.deleteById(id);
    }
}
