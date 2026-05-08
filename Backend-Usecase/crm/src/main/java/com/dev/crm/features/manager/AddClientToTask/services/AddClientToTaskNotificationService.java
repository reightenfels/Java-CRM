package com.dev.crm.features.manager.AddClientToTask.services;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.User;
import com.dev.crm.queues.dto.notifications.AddClientToTask;
import com.dev.crm.queues.producers.NotificationProducer;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@Service
public class AddClientToTaskNotificationService {
    private final NotificationProducer notificationProducer;
    private final UserRepository userRepository;

    public AddClientToTaskNotificationService(
        NotificationProducer notificationProducer,
        UserRepository userRepository
    ) {
        this.notificationProducer = notificationProducer;
        this.userRepository = userRepository;
    }

    public void send(
        Long taskId,
        Long newClientId
    ) {
        User newClient = userRepository.findById(newClientId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден клиент с ID: " + newClientId)
        );

        notificationProducer.send(
            AddClientToTask.builder()
            .type("AddClientToTask")
            .timestamp(System.currentTimeMillis())
            .taskId(taskId)
            .newClientId(newClientId)
            .newClientLogin(newClient.getLogin())
            .build()
        );
    }
}
