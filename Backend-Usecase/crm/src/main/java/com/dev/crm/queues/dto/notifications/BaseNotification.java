package com.dev.crm.queues.dto.notifications;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
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
    @JsonSubTypes.Type(value = SendMessageToTask.class, name = "SendMessageToTask")
})
@Data
@SuperBuilder
public abstract class BaseNotification {
    protected String type;
    protected Long timestamp;
}
