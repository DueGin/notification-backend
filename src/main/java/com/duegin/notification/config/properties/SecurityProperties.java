package com.duegin.notification.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author DueGin
 * @date 2025/1/15
 */
@Data
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private String[] excludes;
}
