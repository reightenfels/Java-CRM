package com.dev.crm.features.employee.GetAllStatuses;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.entities.TaskStatus;
import com.dev.crm.features.employee.GetAllStatuses.contracts.GetAllStatusesContract;
import com.dev.crm.features.employee.GetAllStatuses.dto.GetAllStatusesResponse;

@Service
public class GetAllStatusesUseCase {
    private final GetAllStatusesContract getAllStatusesService;

    public GetAllStatusesUseCase(GetAllStatusesContract getAllStatusesService) {
        this.getAllStatusesService = getAllStatusesService;
    }

    @Transactional
    public GetAllStatusesResponse handle() {
        List<TaskStatus> statusesList = getAllStatusesService.getAllStatuses();

        return new GetAllStatusesResponse(
            statusesList
        );
    }
}
