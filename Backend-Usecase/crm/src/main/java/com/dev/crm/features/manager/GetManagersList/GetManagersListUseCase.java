package com.dev.crm.features.manager.GetManagersList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.crm.features.manager.GetManagersList.contracts.GetManagersListContract;
import com.dev.crm.features.manager.GetManagersList.dto.GetManagersListResponse;

@Service
public class GetManagersListUseCase {
    private final GetManagersListContract getManagersService;

    public GetManagersListUseCase(GetManagersListContract getManagersService) {
        this.getManagersService = getManagersService;
    }

    @Transactional
    public GetManagersListResponse handle() {
        List<String> managers = getManagersService.getAllManagersLogins();

        return new GetManagersListResponse(
            managers
        );
    }
}
