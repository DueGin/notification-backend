package com.duegin.notification.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * 频道授权 实体类。
 *
 * @author DueGin
 * @since 1.0
 */
@Accessors(chain = true)
@Data
@ApiModel(value = "频道授权", description = "频道授权")
@Table(value = "channel_auth")
public class ChannelAuth {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 订阅通知ID
     */
    @ApiModelProperty(value = "订阅通知ID")
    @Column(value = "channel_id")
    private Integer channelId;

    /**
     * 私钥
     */
    @ApiModelProperty(value = "私钥")
    @Column(value = "private_key")
    private String privateKey;

    /**
     * 公钥
     */
    @ApiModelProperty(value = "公钥")
    @Column(value = "public_key")
    private String publicKey;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(value = "create_time")
    private Date createTime;

    @Column(value = "deleted")
    private Integer deleted;


}
