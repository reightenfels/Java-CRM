package com.dev.crm.features.employee.SendMessageToEmployee.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.dev.crm.entities.Attachment;
import com.dev.crm.entities.PersonalEmployeeDialog;
import com.dev.crm.entities.PersonalEmployeeDialogMessage;
import com.dev.crm.entities.User;
import com.dev.crm.features.employee.SendMessageToEmployee.contracts.SendMessageToEmployeeContract;
import com.dev.crm.repositories.EmployeeDialogMessagesRepository;
import com.dev.crm.repositories.EmployeeDialogRepository;
import com.dev.crm.resterrorconfig.exceptions.SaveEntityException;

@Service
public class SendMessageToEmployeeService implements SendMessageToEmployeeContract {
    private final EmployeeDialogRepository dialogRepository;
    private final EmployeeDialogMessagesRepository messagesRepository;

    public SendMessageToEmployeeService(
        EmployeeDialogRepository dialogRepository,
        EmployeeDialogMessagesRepository messagesRepository
    ) {
        this.dialogRepository = dialogRepository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public Long createDialog(
        Long user1,
        Long user2,
        Long avatarId        
    ) {
        User companion1 = new User();
        companion1.setId(user1);

        User companion2 = new User();
        companion2.setId(user2);

        PersonalEmployeeDialog dialog = new PersonalEmployeeDialog();
        dialog.setCompanion1(companion1);
        dialog.setCompanion2(companion2);

        if (avatarId != null) {
            Attachment avatar = new Attachment();
            avatar.setId(avatarId);

            dialog.setAvatar(avatar);
        }
        
        try {
            dialogRepository.save(dialog);
        }
        catch (Exception e) {
            throw new SaveEntityException("Ошибка при создании диалога с сотрудником: " + e.getMessage());
        }

        return dialog.getId();
    }

    @Override
    public Boolean isDialogExists(Long dialogId) {
        PersonalEmployeeDialog dialog = dialogRepository.findById(dialogId).orElse(null);

        return dialog != null;
    }

    @Override
    public Long createMessage(
        String text,
        Long senderId,
        Long dialogId,
        LocalDateTime createdAt
    ) {
        User sender = new User();
        sender.setId(senderId);

        PersonalEmployeeDialog dialog = new PersonalEmployeeDialog();
        dialog.setId(dialogId);

        PersonalEmployeeDialogMessage message = new PersonalEmployeeDialogMessage();
        message.setText(text);
        message.setSender(sender);
        message.setDialog(dialog);
        message.setCreatedAt(createdAt);

        try {
            messagesRepository.save(message);
        }
        catch (Exception e) {
            throw new SaveEntityException("Ошибка при создании сообщения в диалоге с сотрудником: " + e.getMessage());
        }
        

        return message.getId();
    }
}
