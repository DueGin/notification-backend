package com.duegin.notification.domain.dto;

import lombok.Data;

/**
 * @author DueGin
 * @date 2025/1/13
 */
@Data
public class ChannelSaveDTO {

    private Integer id;

    /**
     * 订阅通知名称
     */
    private String name;

}
