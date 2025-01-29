package com.followup.service.impl;

import com.followup.entity.Postponed;
import com.followup.repository.IPostponedRepository;
import com.followup.service.IPostponedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PostponedDaysServiceImpl implements IPostponedService {

    private final IPostponedRepository postponedRepository;

    @Override
    @Transactional
    public Postponed addPostponed(Postponed postponed) {
        LocalDate now = LocalDate.now();
        postponed.setRenewalOfAddon(now);
        Integer addonDays = postponed.getAddonDays();
        postponed.setExpiryOfAddon(now.plusDays(addonDays));
        return postponedRepository.save(postponed);
    }

    @Override
    public Boolean deletePostponed(Long id) {
        Postponed postponed = postponedRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Postponed not found with id " + id)
        );

        if (!ObjectUtils.isEmpty(postponed)){
            postponedRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
