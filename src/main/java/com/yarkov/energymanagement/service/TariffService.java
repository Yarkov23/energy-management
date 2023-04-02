package com.yarkov.energymanagement.service;

import com.yarkov.energymanagement.entity.Tariff;
import com.yarkov.energymanagement.repository.TariffRepo;
import org.springframework.stereotype.Service;

@Service
public class TariffService extends BaseService<Tariff, Long, TariffRepo> {
    public TariffService(TariffRepo repository) {
        super(repository);
    }
}
