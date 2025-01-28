package com.followup.controller;

import com.followup.entity.Customer;
import com.followup.Response.JsonDeleteResponse;
import com.followup.Response.JsonResponse;
import com.followup.exception.CustomerNotFoundException;
import com.followup.service.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@Tag(name = "Customer APIs")
@CrossOrigin("*")
public class CustomerController {

    private final ICustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity<?> saveCustomer(@Valid @RequestBody Customer customer){
        try {
            Customer addCustomer = customerService.addCustomer(customer);
            return ResponseEntity.ok(addCustomer);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-customer/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){
        try {
            Customer customer = customerService.getCustomer(id);
            return ResponseEntity.ok(customer);
        } catch (CustomerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-customers")
    public ResponseEntity<?> getAllCustomers(){
        try {
            List<Customer> customers = customerService.getCustomers();
            return ResponseEntity.ok(customers);
        } catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonResponse(false, "Customers could not be fetched", e.getMessage()));
        }
    }

    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id){
        Boolean isCustomerDeleted = customerService.deleteCustomer(id);
        if (isCustomerDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new JsonDeleteResponse(true,"Customer Deleted"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new JsonDeleteResponse(false, "Customer is not deleted"));
        }
    }

    @PutMapping("/update-customer/{id}")
    public ResponseEntity<?> updateCategoryById(@RequestBody Customer customer, @PathVariable Long id){
        try {
            customerService.updateCustomer(customer, id);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonDeleteResponse(true,"Customer updated"));
        } catch (CustomerNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonDeleteResponse(false, "Customer not updated"));
        }
    }
}
