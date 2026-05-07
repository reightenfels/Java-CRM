package com.dev.crm.features.manager.OpenClientDialog.dto;

import java.util.List;

import com.dev.crm.entities.ClientDialogMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OpenClientDialogResponse {
    private List<ClientDialogMessage> messages;
}
