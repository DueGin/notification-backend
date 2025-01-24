package com.duegin.notification.service;


import com.duegin.notification.config.base.service.impl.BaseServiceImpl;
import com.duegin.notification.convert.UserConvertor;
import com.duegin.notification.domain.dto.user.UserPageDTO;
import com.duegin.notification.domain.dto.user.UserSaveDTO;
import com.duegin.notification.domain.vo.user.UserVO;
import com.duegin.notification.entity.User;
import com.duegin.notification.mapper.UserMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.If;
import com.mybatisflex.core.query.QueryChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.duegin.notification.entity.table.UserTableDef.USER;

/**
 * 服务层实现。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Service
public class UserService extends BaseServiceImpl<UserMapper, User> {

    @Autowired
    private UserConvertor userConvertor;

    public Page<UserVO> getPage(Page<UserVO> page, UserPageDTO userPageDTO) {
        return QueryChain.of(mapper)
                .from(USER)
                .where(USER.ACCOUNT.eq(userPageDTO.getAccount(), If::hasText))
                .and(USER.USERNAME.eq(userPageDTO.getUsername(), If::hasText))
                .and(USER.EMAIL.eq(userPageDTO.getEmail(), If::hasText))
                .pageAs(page, UserVO.class);
    }

    public Integer insertOrUpdateUser(UserSaveDTO userSaveDTO) {
        User user = userConvertor.userSaveDTOToUser(userSaveDTO);

        mapper.insertOrUpdateSelective(user);
        return user.getId();
    }
}