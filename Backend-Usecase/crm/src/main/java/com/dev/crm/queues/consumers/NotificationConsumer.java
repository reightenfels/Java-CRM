package com.dev.crm.queues.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.dev.crm.queues.config.RabbitConfig;
import com.dev.crm.queues.dto.notifications.BaseNotification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final SimpMessagingTemplate messagingTemplate;

    @RabbitListener(
            queues = RabbitConfig.QUEUE,
            containerFactory = "rabbitListenerContainerFactory"
    )
    public void handle(BaseNotification notification) {      
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }
}
