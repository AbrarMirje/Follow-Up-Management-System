package com.followup.controller;


import com.followup.Response.JsonResponse;
import com.followup.entity.dto.RentDto;
import com.followup.service.IRentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
@CrossOrigin("*")
@Tag(name = "Rent APIs")
public class RentController {

    private final IRentService rentService;

    @PostMapping("/add-rent")
    public ResponseEntity<?> saveRent(@Valid @RequestBody RentDto rentDto) {
        System.out.println(rentDto.getNumberOfDaysToBeIncreased());
        RentDto savedRent = rentService.addRent(rentDto);
        return ResponseEntity.ok(savedRent);
    }


    @GetMapping("/get-rent/{id}")
    public ResponseEntity<?> getRentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(rentService.getRent(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Error retrieving rent", e.getMessage()));
        }
    }

    @GetMapping("/get-rents")
    public ResponseEntity<?> getAllRents() {
        try {
            return ResponseEntity.ok(rentService.getAllRent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Error retrieving rents", e.getMessage()));
        }
    }

    @DeleteMapping("/delete-rent/{id}")
    public ResponseEntity<?> deleteRentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(rentService.deleteRent(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Error deleting rent", e.getMessage()));
        }
    }

    @PutMapping("/disable-rent/{id}")
    public ResponseEntity<?> disableRentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(rentService.disableRent(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Error disabling rent", e.getMessage()));
        }
    }

    @GetMapping("/get-rents-by-ongoing-true")
    public ResponseEntity<?> getRentsByOngoingTrue() {
        try {
            return ResponseEntity.ok(rentService.getRentsByOngoingIsTrue());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Error retrieving rents", e.getMessage()));
        }
    }

    @GetMapping("/get-rents-by-ongoing-false")
    public ResponseEntity<?> getRentsByOngoingFalse() {
        try {
            return ResponseEntity.ok(rentService.getRentsByOngoingIsFalse());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Error retrieving rents", e.getMessage()));
        }
    }

    @GetMapping("/get-expired-rents")
    public ResponseEntity<?> getExpiredRents() {
        try {
            return ResponseEntity.ok(rentService.getExpiredRents());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Error retrieving rents", e.getMessage()));
        }
    }

}
