package com.duegin.notification.config.base.service.impl;

import com.duegin.notification.config.base.service.BaseService;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * @author DueGin
 * @date 2025/1/2
 */
public class BaseServiceImpl <M extends BaseMapper<E>, E> extends ServiceImpl<M, E> implements BaseService {
}
