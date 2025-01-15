package com.duegin.notification.convert;

import com.duegin.notification.domain.dto.user.RegisterDTO;
import com.duegin.notification.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * @author DueGin
 * @date 2025/1/14
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserConvertor {

    User registerDTOToUser(RegisterDTO registerDTO);
}
