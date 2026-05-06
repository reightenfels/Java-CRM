package com.dev.crm.features.employee.DeleteEmployeeDialog.contracts;

public interface DeleteEmployeeContract {
    /**
     * Удаляет диалог по ID
     * @param dialogId - ID диалога
     * @return ID удаленного диалога
     */
    public Long deleteDialog(Long dialogId);

    /**
     * Удаляет все сообщения диалога
     * @param dialogId - ID диалога
     * @return Количество удаленных сообщений
     */
    public Long deleteAllDialogMessages(Long dialogId);
}
