package com.dev.crm.contracts.services;

import java.util.List;
import java.util.Optional;

import com.dev.crm.entities.Client;

public interface ClientServiceContract {
    /**
     * Возвращает список всех клиентов
     * @return - список клиентов
     */
    public List<Client> findAll();

    /**
     * Создает клиента
     * @param Client client
     * @return - ID созданного клиента
     */
    public Long create(Client client);

    /**
     * Обновляет клиента
     * @param Client client;
     * @return - ID обновленного клиента
     */
    public Long update(Client client);

    /**
     * Удаляет клиента
     * @param Long clientId;
     * @return - ID удаленного клиента
     */
    public Long delete(Long clientId);

    /**
     * Возвращает клиента по ID
     * @param Long clientId
     * @return - Модель клиента
     */
    public Optional<Client> findById(Long clientId);

    /**
     * Обозначает клиента редактируемым в данный момент
     * @param Long clientId
     * @return - Статус выполнения
     */
    public Boolean makeEditingOn(Long clientId);

    /**
     * Снимает флаг редактируемости в данный момент с клиента
     * @param Long clientId
     * @return - Статус выполнения
     */
    public Boolean makeEditingOff(Long clientId);

    /**
     * Поиск клиентов
     * @param String search
     * @return - Список клиентов, соответствующих поисковому запросу
     */
    public List<Client> search(String search);
}
