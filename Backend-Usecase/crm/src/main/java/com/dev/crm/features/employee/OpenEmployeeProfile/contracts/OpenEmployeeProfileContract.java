package com.dev.crm.features.employee.OpenEmployeeProfile.contracts;

import com.dev.crm.features.employee.OpenEmployeeProfile.dto.OpenEmployeeProfileDTO;

public interface  OpenEmployeeProfileContract {
    /**
     * Получает данные сотрудника по логину
     * @param login - логин сотрудника
     */
    public OpenEmployeeProfileDTO getEmployeeByLogin(String login);
}
