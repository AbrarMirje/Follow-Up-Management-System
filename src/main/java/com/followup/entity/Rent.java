package com.followup.entity;

import com.followup.entity.Customer;
import com.followup.entity.Material;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentId;

    private LocalDate issuedDate;
    private Integer numberOfDaysToBeIncreased;
    private LocalDate renewDate;

    private Double quantity;
    private Double totalAmount;
    private Boolean isRentOngoing = false;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;


}
