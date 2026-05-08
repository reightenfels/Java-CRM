package com.dev.crm.features.manager.OpenTaskCard.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dev.crm.queues.dto.notifications.OpenTask;
import com.dev.crm.queues.producers.NotificationProducer;
import com.dev.crm.repositories.TaskEditorsRepository;

@Service
public class OpenTaskCardNotificationService {
    private final NotificationProducer notificationProducer;
    private final TaskEditorsRepository editorsRepository;

    public OpenTaskCardNotificationService(
        NotificationProducer notificationProducer,
        TaskEditorsRepository editorsRepository
    ) {
        this.notificationProducer = notificationProducer;
        this.editorsRepository = editorsRepository;
    }

    public void send(Long taskId) {
        Set<String> editorsLogins = editorsRepository.findTaskEditorsLogins(taskId);

        notificationProducer.send(
            OpenTask.builder()
            .type("OpenTask")
            .timestamp(System.currentTimeMillis())
            .taskId(taskId)
            .taskEditorsLogins(editorsLogins)
            .build()
        );
    }
}
