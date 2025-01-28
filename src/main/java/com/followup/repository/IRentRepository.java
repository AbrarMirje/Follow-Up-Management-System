package com.followup.repository;

import com.followup.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IRentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByIsRentOngoingTrue();
    List<Rent> findByIsRentOngoingFalse();

//    List<Rent> findAllByRenewDateBeforeOrRenewDate(LocalDate date);
    List<Rent> findAllByRenewDate(LocalDate date);

}
