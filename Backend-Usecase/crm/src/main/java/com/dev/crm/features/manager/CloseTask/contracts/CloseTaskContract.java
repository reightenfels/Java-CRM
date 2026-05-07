package com.dev.crm.features.manager.CloseTask.contracts;

public interface CloseTaskContract {
    /**
     * Убирает пользователя из редакторов задачи
     * @param userId - ID пользователя
     * @param taskId - ID задачи
     * @return - Кол-во удаленных строк
     */
    public Long removeUserFromTaskEditors(Long userId, Long taskId);
}
