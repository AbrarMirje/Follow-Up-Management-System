package com.followup.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private Long id;
    private Double quantity;
    private LocalDate issuedDate;
    private LocalDate renewDate;
    private Long customerId;
    private Long materialId;
}


