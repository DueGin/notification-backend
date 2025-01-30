package com.duegin.notification.domain.dto.channel.user;

import com.duegin.notification.domain.dto.channel.user.subscribe.ChannelUserSubscribePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 用户订阅频道分页DTO
 *
 * @author DueGin
 * @date 2025/1/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChannelUserPageDTO extends ChannelUserSubscribePageDTO {
    private Integer channelId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTimeTo;
}
