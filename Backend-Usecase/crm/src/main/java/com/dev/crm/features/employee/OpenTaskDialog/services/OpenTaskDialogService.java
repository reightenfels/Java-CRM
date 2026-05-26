package com.dev.crm.features.employee.OpenTaskDialog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.TaskDialogMessage;
import com.dev.crm.features.employee.OpenTaskDialog.contracts.OpenTaskDialogContract;
import com.dev.crm.features.employee.OpenTaskDialog.dto.OpenTaskDialogMessageDTO;
import com.dev.crm.repositories.TaskDialogMessagesRepository;

@Service
public class OpenTaskDialogService implements OpenTaskDialogContract {
    private final TaskDialogMessagesRepository messagesRepository;

    public OpenTaskDialogService(TaskDialogMessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<OpenTaskDialogMessageDTO> getAllDialogMessages(Long dialogId, Long currentUserId) {
        List<TaskDialogMessage> messages = messagesRepository.findByDialogId(dialogId);

        List<OpenTaskDialogMessageDTO> messagesDto = messages.stream()
            .map(message -> {
                return new OpenTaskDialogMessageDTO(
                    message.getText(),
                    message.getSender().getLogin(),
                    message.getCreatedAt(),
                    message.getSender().getId().equals(currentUserId),
                    null
                );
            })
            .collect(Collectors.toList());

        return messagesDto;
    }
}
