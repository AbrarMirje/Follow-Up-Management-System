package com.followup.service.impl;

import com.followup.entity.Material;
import com.followup.exception.MaterialNotFoundException;
import com.followup.repository.IMaterialRepository;
import com.followup.service.IMaterialService;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements IMaterialService {

    private final IMaterialRepository materialRepository;


    @Override
    @Transactional
    public Material addMaterial(Material material) {
        if (material.getPerMaterialAmount() != null && material.getMaterialQuantity() != null) {
            Double finalAmount = material.getPerMaterialAmount() * material.getMaterialQuantity();
            material.setTotalMaterialAmount(finalAmount);
        } else {
            throw new IllegalArgumentException("Per material amount and quantity must not be null");
        }

        material.setMaterialIssuedDate(LocalDate.now());
        return materialRepository.save(material);
    }

    @Override
    public Material getMaterial(Long id) {
        return materialRepository.findById(id).orElseThrow(
                () -> new MaterialNotFoundException("Material not found with id " + id)
        );
    }

    @Override
    public List<Material> getMaterials() {
        List<Material> materialList = materialRepository.findAll();
        return materialList.isEmpty() ? new ArrayList<>() : materialList;
    }

    @Override
    @Transactional
    public Boolean deleteMaterial(Long id) {
        materialRepository.findById(id).orElseThrow(
                () -> new MaterialNotFoundException("Material not found with id " + id)
        );
        materialRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public void updateMaterial(Material material, Long id) {
        Material existedMaterial = materialRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Material not found with id: " + id));

        // Updating Material Name
        if (material.getMaterialName() != null){
            existedMaterial.setMaterialName(material.getMaterialName());
        } else {
            existedMaterial.setMaterialName(existedMaterial.getMaterialName());
        }

        if (material.getDescription() != null){
            existedMaterial.setDescription(material.getDescription());
        } else {
            existedMaterial.setDescription(existedMaterial.getDescription());
        }

        if (material.getMaterialCondition() != null){
            existedMaterial.setMaterialCondition(material.getMaterialCondition());
        } else {
            existedMaterial.setMaterialCondition(existedMaterial.getMaterialCondition());
        }

        if (material.getMaterialType() != null){
            existedMaterial.setMaterialType(material.getMaterialType());
        } else {
            existedMaterial.setMaterialType(existedMaterial.getMaterialType());
        }

        if (material.getMaterialIssuedDate() != null){
            existedMaterial.setMaterialIssuedDate(material.getMaterialIssuedDate());
        } else {
            existedMaterial.setMaterialIssuedDate(existedMaterial.getMaterialIssuedDate());
        }


        if (material.getPerMaterialAmount() != null){
            existedMaterial.setPerMaterialAmount(material.getPerMaterialAmount());
        } else {
            existedMaterial.setPerMaterialAmount(existedMaterial.getPerMaterialAmount());
        }

        if (material.getMaterialQuantity() != null){
            existedMaterial.setMaterialQuantity(material.getMaterialQuantity());
        } else {
            existedMaterial.setMaterialQuantity(existedMaterial.getMaterialQuantity());
        }

        if (material.getTotalMaterialAmount() != null && material.getMaterialQuantity() != null){
            Double finalAmount = material.getTotalMaterialAmount() * material.getMaterialQuantity();
            existedMaterial.setTotalMaterialAmount(finalAmount);
        } else {
            existedMaterial.setTotalMaterialAmount(existedMaterial.getTotalMaterialAmount());
        }

        materialRepository.save(existedMaterial);
    }
}
