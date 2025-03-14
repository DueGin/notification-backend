package com.duegin.notification.sse;

import com.duegin.notification.sse.domain.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SseService {
    /**
     * messageId的 SseEmitter对象映射集
     */
    private static final Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    /**
     * 连接sse
     * @param uuid
     * @return
     */
    public SseEmitter connect(String uuid) {
        // 默认30s过期，0为不过期
        SseEmitter sseEmitter = new SseEmitter(0L);

        // 连接成功需要返回数据，否则会出现待处理状态
        try {
            sseEmitter.send(SseEmitter.event().comment("welcome"));
        } catch (IOException e) {
            log.error("send message error", e);
        }

        // 连接断开
        sseEmitter.onCompletion(() -> {
            sseEmitterMap.remove(uuid);
        });

        // 连接超时
        sseEmitter.onTimeout(() -> {
            sseEmitterMap.remove(uuid);
        });

        // 连接报错
        sseEmitter.onError((throwable) -> {
            sseEmitterMap.remove(uuid);
        });

        sseEmitterMap.put(uuid, sseEmitter);

        return sseEmitter;
    }

    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(Message message) {
        message.setTotal(sseEmitterMap.size());

        sseEmitterMap.forEach((uuid, sseEmitter) -> {
            try {
                sseEmitter.send(message, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}