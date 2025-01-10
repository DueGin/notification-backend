package com.duegin.notification.config.base.service.impl;


import org.springframework.stereotype.Service;
import com.duegin.notification.service.ChannelAuthService;
import com.duegin.notification.entity.ChannelAuth;
import com.duegin.notification.mapper.ChannelAuthMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 频道授权 服务层实现。
 *
 * @author DueGin
 * @since 1.0
 */
@Service
public class ChannelAuthServiceImpl extends ServiceImpl<ChannelAuthMapper, ChannelAuth> implements ChannelAuthService {

}