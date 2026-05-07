package com.dev.crm.features.manager.CreateClient.contracts;

public interface CreateClientContract {
    /**
     * Проверяет уникальность логина, почты, телефона клиента
     * @param login - Логин клиента
     * @param email - Почта клиента
     * @param phone - Телефон клиента
     * @return - Уникален\нет
     */
    public Boolean isClientDataUnique(
        String login,
        String email,
        String phone
    );

    /**
     * Создает клиента
     * @param login - Логин клиента
     * @param password - Пароль учетной записи клиента
     * @param firstName - Имя клиента
     * @param lastName - Фамилия клиента
     * @param phone - Телефон клиента
     * @param email - Почта клиента
     * @return - ID созданного клиента
     */
    public Long createClient(
        String login,
        String password,
        String firstName,
        String lastName,
        String phone,
        String email
    );
}
