package com.dev.crm.features.manager.DeleteTask.contracts;

public interface DeleteTaskContract {
    /**
     * Проверяет есть ли редакторы у задачи
     * @param Long taskId - ID задачи
     * @return Есть\нет
     */
    public Boolean existsTaskEditors(Long taskId);

    /**
     * Удаляет задачу
     * @param Long taskId - ID задачи
     * @param int version - Версия задачи
     * @return - Кол-во удаленных строк
     */
    public Long deleteTask(Long taskId, int version);

    /**
     * Удаляет диалог по задаче
     * @param Long taskId - ID задачи
     * @return Кол-во удаленных строк
     */
    public Long deleteTaskDialog(Long taskId);

    /**
     * Удаляет сообщения диалога по задаче
     * @param Long taskId - ID задачи
     * @return Long
     */
    public Long deleteTaskDialogMessages(Long taskId);
}
