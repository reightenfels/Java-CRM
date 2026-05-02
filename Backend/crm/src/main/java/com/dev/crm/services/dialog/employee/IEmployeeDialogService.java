package com.dev.crm.services.dialog.employee;

import java.util.List;

import com.dev.crm.entities.PersonalEmployeeDialog;

public interface IEmployeeDialogService {
    /**
     * Возвращает все личные диалоги с сотрудниками
     * @return - Список диалогов
     */
    public List<PersonalEmployeeDialog> findAll();

    /**
     * Возвращает диалоги с пользователями, логин которых соответствует поисковому запросу
     * @param String login
     * @return - Список диалогов
     */
    public List<PersonalEmployeeDialog> findByCompanionLogin(String login);
}
