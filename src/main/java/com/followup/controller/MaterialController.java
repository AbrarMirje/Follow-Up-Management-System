package com.followup.controller;

import com.followup.Response.JsonDeleteResponse;
import com.followup.Response.JsonResponse;
import com.followup.entity.Material;
import com.followup.exception.MaterialNotFoundException;
import com.followup.service.IMaterialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/material")
@Tag(name = "Material APIs")
@CrossOrigin("*")
public class MaterialController {

    private final IMaterialService materialService;

    @PostMapping("/add-material")
    public ResponseEntity<?> saveMaterial(@Valid @RequestBody Material material){
        try {
            Material addedMaterial = materialService.addMaterial(material);
            return ResponseEntity.ok(addedMaterial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(false, "Error adding material", e.getMessage()));
        }
    }

    @GetMapping("/get-material/{id}")
    public ResponseEntity<?> getMaterialById(@PathVariable Long id){
        try {
            Material material = materialService.getMaterial(id);
            return new ResponseEntity<>(material, HttpStatus.FOUND);
        } catch (MaterialNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponse(false, "Material not found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Error retrieving material", e.getMessage()));
        }
    }

    @GetMapping("/get-materials")
    public ResponseEntity<?> getMaterialById(){
        try {
            List<Material> materials = materialService.getMaterials();
            return ResponseEntity.ok(materials);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Error retrieving materials", e.getMessage()));
        }
    }

    @DeleteMapping("/delete-material/{id}")
    public ResponseEntity<?> deleteMaterialById(@PathVariable Long id){
        try {
            Boolean isDeleted = materialService.deleteMaterial(id);
            if (isDeleted){
                return ResponseEntity.status(HttpStatus.OK).body(new JsonDeleteResponse(true, "Material deleted"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonDeleteResponse(false, "Material not deleted"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Error deleting material", e.getMessage()));
        }
    }

    @PutMapping("/update-material/{id}")
    public ResponseEntity<?> updateMaterialById(@RequestBody Material material, @PathVariable Long id){
        try {
            materialService.updateMaterial(material, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MaterialNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponse(false, "Material not found", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponse(false, "Error updating material", e.getMessage()));
        }
    }
}
