package com.dev.crm.features.manager.CloseTask.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.dev.crm.queues.dto.notifications.CloseTask;
import com.dev.crm.queues.producers.NotificationProducer;
import com.dev.crm.repositories.TaskEditorsRepository;

@Service
public class CloseTaskNotificationService {
    private final NotificationProducer notificationProducer;
    private final TaskEditorsRepository editorsRepository;

    public CloseTaskNotificationService(
        NotificationProducer notificationProducer,
        TaskEditorsRepository editorsRepository
    ) {
        this.notificationProducer = notificationProducer;
        this.editorsRepository = editorsRepository;
    }

    public void send(
        Long taskId
    ) {
        Set<String> editorsLogins = editorsRepository.findTaskEditorsLogins(taskId);

        notificationProducer.send(
            CloseTask.builder()
            .type("CloseTask")
            .timestamp(System.currentTimeMillis())
            .taskId(taskId)
            .editorsLogins(editorsLogins)
            .build()
        );
    }
}
