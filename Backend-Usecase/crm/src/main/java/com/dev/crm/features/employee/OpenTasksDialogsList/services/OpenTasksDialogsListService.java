package com.dev.crm.features.employee.OpenTasksDialogsList.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.TaskDialog;
import com.dev.crm.entities.TaskDialogMessage;
import com.dev.crm.features.employee.OpenTasksDialogsList.contracts.OpenTasksDialogsListContract;
import com.dev.crm.features.employee.OpenTasksDialogsList.dto.EmployeeDialogDTO;
import com.dev.crm.repositories.TaskDialogMessagesRepository;
import com.dev.crm.repositories.TaskDialogRepository;

@Service
public class OpenTasksDialogsListService implements OpenTasksDialogsListContract {
    private final TaskDialogRepository dialogRepository;
    private final TaskDialogMessagesRepository messagesRepository;

    public OpenTasksDialogsListService(
        TaskDialogRepository dialogRepository,
        TaskDialogMessagesRepository messagesRepository
    ) {
        this.dialogRepository = dialogRepository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<EmployeeDialogDTO> getUserActiveTasksDialogs(Long userId) {
        List<TaskDialog> dialogs = dialogRepository.findAllUseActiveDialogs(userId);

        List<EmployeeDialogDTO> dialgsDTO = dialogs.stream()
            .map(dialog -> {
                TaskDialogMessage lastMessage = messagesRepository.findTopByDialogIdOrderByIdDesc(dialog.getId()).orElse(null);
                
                return new EmployeeDialogDTO(
                    dialog.getTask().getTitle(),
                    lastMessage != null ? lastMessage.getText() : "",
                    lastMessage != null ? lastMessage.getCreatedAt() : null,
                    lastMessage != null ? lastMessage.getSender().getLogin() : null
                );
            })
            .collect(Collectors.toList());

        return dialgsDTO;
    }
}
