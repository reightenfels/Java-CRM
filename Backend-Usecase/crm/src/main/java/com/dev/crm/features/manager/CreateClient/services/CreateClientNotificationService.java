package com.dev.crm.features.manager.CreateClient.services;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.User;
import com.dev.crm.queues.dto.notifications.CreateClient;
import com.dev.crm.queues.producers.NotificationProducer;
import com.dev.crm.repositories.UserRepository;
import com.dev.crm.resterrorconfig.exceptions.ResourceNotFoundException;

@Service
public class CreateClientNotificationService {
    private final NotificationProducer notificationProducer;
    private final UserRepository userRepository;

    public CreateClientNotificationService(
        NotificationProducer notificationProducer,
        UserRepository userRepository
    ) {
        this.notificationProducer = notificationProducer;
        this.userRepository = userRepository;
    }

    public void send(
        Long newClientId
    ) {
        User newClient = userRepository.findById(newClientId).orElseThrow(
            () -> new ResourceNotFoundException("Не найден клиент с ID: " + newClientId)
        );

        notificationProducer.send(
            CreateClient.builder()
            .type("CreateClient")
            .timestamp(System.currentTimeMillis())
            .newClient(newClient)
            .build()
        );
    }
}
