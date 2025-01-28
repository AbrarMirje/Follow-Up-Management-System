package com.followup.repository;

import com.followup.entity.AddonDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddonRepository extends JpaRepository<AddonDays, Long> {
}
