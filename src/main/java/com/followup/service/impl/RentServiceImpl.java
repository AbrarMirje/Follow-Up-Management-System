package com.followup.service.impl;

import com.followup.entity.Customer;
import com.followup.entity.Material;
import com.followup.entity.Rent;
import com.followup.entity.dto.RentDto;
import com.followup.exception.MaterialNotFoundException;
import com.followup.exception.RentNotAvailableException;
import com.followup.repository.ICustomerRepository;
import com.followup.repository.IMaterialRepository;
import com.followup.repository.IRentRepository;
import com.followup.service.IRentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RentServiceImpl implements IRentService {

    private final IRentRepository rentRepository;

    private final IMaterialRepository materialRepository;

    private final ICustomerRepository customerRepository;
    public final ModelMapper mapper;

    @Override
    @Transactional
    public RentDto addRent(RentDto rentDto) {
        Rent rent = new Rent();

        rent.setQuantity(rentDto.getQuantity());

        Material material = materialRepository.findById(rentDto.getMaterialId())
                .orElseThrow(() -> new RuntimeException("Material not found"));
        rent.setMaterial(material);

        Customer customer = customerRepository.findById(rentDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        rent.setCustomer(customer);

        if (material.getPerMaterialAmount() != null && rentDto.getQuantity() != null) {
            double totalAmount = material.getPerMaterialAmount() * rentDto.getQuantity();
            rent.setTotalAmount(totalAmount);
        }

        LocalDate issuedDate = LocalDate.now();
        rent.setIssuedDate(issuedDate);

        LocalDate renewDate = issuedDate.plusDays(0);
        rent.setRenewDate(renewDate);

        rent.setIsRentOngoing(true);

        Rent savedRent = rentRepository.save(rent);

        RentDto responseDto = new RentDto();
        responseDto.setQuantity(savedRent.getQuantity());
        responseDto.setCustomerId(savedRent.getCustomer().getCustomerId());
        responseDto.setMaterialId(savedRent.getMaterial().getMaterialId());
        return responseDto;
    }


    @Override
    public Rent getRent(Long id) {
        return rentRepository.findById(id).orElseThrow(
                () -> new RentNotAvailableException("Rent not available with id " + id)
        );
    }

    @Override
    public List<Rent> getAllRent() {
        List<Rent> rentList = rentRepository.findAll();
        return rentList.isEmpty() ? new ArrayList<>() : rentList;
    }


    @Override
    public Boolean deleteRent(Long id) {
        rentRepository.findById(id).orElseThrow(
                () -> new RentNotAvailableException("Rent not available with id " + id)
        );
        rentRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean disableRent(Long id) {
        Rent findRent = rentRepository.findById(id).orElseThrow(
                () -> new MaterialNotFoundException("Material not found with id " + id)
        );
        if (findRent.getIsRentOngoing()) {
            findRent.setIsRentOngoing(false);
            rentRepository.save(findRent);
        }
        return true;
    }

    @Override
    public List<Rent> getRentsByOngoingIsTrue() {
        List<Rent> byIsRentOngoingTrue = rentRepository.findByIsRentOngoingTrue();
        return byIsRentOngoingTrue.isEmpty() ? new ArrayList<>() : byIsRentOngoingTrue;
    }

    @Override
    public List<Rent> getRentsByOngoingIsFalse() {
        List<Rent> byIsRentOngoingFalse = rentRepository.findByIsRentOngoingFalse();
        return byIsRentOngoingFalse.isEmpty() ? new ArrayList<>() : byIsRentOngoingFalse;
    }


    @Override
    public List<RentDto> getExpiredRents() {
        LocalDate today = LocalDate.now();
        List<Rent> expiredRents = rentRepository.findAllByRenewDate(today);

        return expiredRents.stream()
                .map(rent -> {
                    RentDto rentDto = new RentDto();
                    rentDto.setQuantity(rent.getQuantity());
                    rentDto.setCustomerId(rent.getCustomer().getCustomerId());
                    rentDto.setMaterialId(rent.getMaterial().getMaterialId());
                    rentDto.setRenewDate(rent.getRenewDate());
                    rentDto.setIssuedDate(rent.getIssuedDate());
                    return rentDto;
                })
                .collect(Collectors.toList());
    }

}
