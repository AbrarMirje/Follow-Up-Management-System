package com.followup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "postponed")
public class Postponed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer addonDays;
    private LocalDate renewalOfAddon;
    private LocalDate expiryOfAddon;
    private Double chargesOnAddon;

    @ManyToMany
    @JoinTable(
            name = "postponed_rent",
            joinColumns = @JoinColumn(name = "postponed_id"),
            inverseJoinColumns = @JoinColumn(name = "rent_id")
    )
    private List<Rent> rents = new ArrayList<>();

}