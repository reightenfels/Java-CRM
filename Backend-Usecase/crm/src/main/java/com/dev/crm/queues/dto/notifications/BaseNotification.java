package com.dev.crm.queues.dto.notifications;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DeleteEmployeeDialog.class, name = "DeleteEmployeeDialog"),
    @JsonSubTypes.Type(value = SendMessageToEmployee.class, name = "SendMessageToEmployee"),
    @JsonSubTypes.Type(value = SendMessageToTask.class, name = "SendMessageToTask"),
    @JsonSubTypes.Type(value = AddClientToTask.class, name = "AddClientToTask"),
    @JsonSubTypes.Type(value = ChangeTaskStatus.class, name = "ChangeTaskStatus"),
    @JsonSubTypes.Type(value = CloseTask.class, name = "CloseTask"),
    @JsonSubTypes.Type(value = CreateClient.class, name = "CreateClient"),
    @JsonSubTypes.Type(value = CreateTask.class, name = "CreateTask"),
    @JsonSubTypes.Type(value = CreateTaskDialog.class, name = "CreateTaskDialog"),
    @JsonSubTypes.Type(value = DeleteClient.class, name = "DeleteClient"),
    @JsonSubTypes.Type(value = DeleteTask.class, name = "DeleteTask"),
    @JsonSubTypes.Type(value = DeleteTaskDialog.class, name = "DeleteTaskDialog"),
    @JsonSubTypes.Type(value = OpenTask.class, name = "OpenTask"),
    @JsonSubTypes.Type(value = SendMessageToClient.class, name = "SendMessageToClient"),
})
@Data
@SuperBuilder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED) 
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public abstract class BaseNotification {
    protected String type;
    protected Long timestamp;
}
