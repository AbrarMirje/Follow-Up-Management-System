package com.followup.service;

import com.followup.entity.Material;

import java.util.List;

public interface IMaterialService {

    Material addMaterial(Material material);

    Material getMaterial(Long id);

    List<Material> getMaterials();

    Boolean deleteMaterial(Long id);

    void updateMaterial(Material material, Long id);
}
