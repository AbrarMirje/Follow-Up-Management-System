package com.followup.service;

import com.followup.entity.Customer;
import com.followup.entity.Rent;
import com.followup.entity.dto.RentDto;

import java.util.List;

public interface IRentService {

    RentDto addRent(RentDto rentDto);

    Rent getRent(Long id);

    List<Rent> getAllRent();

    Boolean deleteRent(Long id);

    Boolean disableRent(Long id);

    List<Rent> getRentsByOngoingIsTrue();
    List<Rent> getRentsByOngoingIsFalse();

    List<RentDto> getExpiredRents();

}
