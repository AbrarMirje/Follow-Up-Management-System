package com.followup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;
    private String materialName;
    private String description;
    private String materialCondition;
    private String materialType;
    private LocalDate materialIssuedDate;
    private Double perMaterialAmount;
    private Double materialQuantity;
    private Double totalMaterialAmount;
}
