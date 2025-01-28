package com.followup.service.impl;

import com.followup.entity.AddonDays;
import com.followup.entity.Rent;
import com.followup.repository.IAddonRepository;
import com.followup.repository.IRentRepository;
import com.followup.service.IAddonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddonDaysServiceImpl implements IAddonService {

    private final IAddonRepository addonRepository;

    @Override
    public AddonDays addAddonDays(AddonDays addonDays) {
       addonDays.setExpiryOfAddon(LocalDate.now());
       return addonRepository.save(addonDays);
    }

}
