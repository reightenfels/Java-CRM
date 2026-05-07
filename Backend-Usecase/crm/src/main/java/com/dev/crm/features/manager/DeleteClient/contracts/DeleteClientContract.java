package com.dev.crm.features.manager.DeleteClient.contracts;

public interface DeleteClientContract {
    /**
     * Удаляет клиента
     * @param clientId - ID клиента
     * @return - ID удаленного клиента
     */
    public Long deleteClient(Long clientId);
}
