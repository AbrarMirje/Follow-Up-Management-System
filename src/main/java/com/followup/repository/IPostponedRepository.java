package com.followup.repository;

import com.followup.entity.Postponed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostponedRepository extends JpaRepository<Postponed, Long> {
}
