package com.followup.service.impl;

import com.followup.entity.Customer;
import com.followup.exception.CustomerNotFoundException;
import com.followup.repository.ICustomerRepository;
import com.followup.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        customer.setCustomerEntryDate(LocalDate.now());
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer not found with id " + id)
        );
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.isEmpty() ? new ArrayList<>() : customers;
    }

    @Override
    @Transactional
    public Boolean deleteCustomer(Long id) {
        customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer not found with id " + id));

        customerRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public void updateCustomer(Customer customer, Long id) {

        Customer existedCustomer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException("Customer Not Found With Id " + id));

        // Updating Customer Name
        if (customer.getCustomerName() != null){
            existedCustomer.setCustomerName(customer.getCustomerName());
        } else {
            existedCustomer.setCustomerName(existedCustomer.getCustomerName());
        }

        // Updating Customer Address
        if (customer.getSiteAddress() != null){
            existedCustomer.setSiteAddress(customer.getSiteAddress());
        } else {
            existedCustomer.setSiteAddress(existedCustomer.getSiteAddress());
        }

        // Update Customer Date
        if (customer.getCustomerEntryDate() != null){
            existedCustomer.setCustomerEntryDate(customer.getCustomerEntryDate());
        } else {
            existedCustomer.setCustomerEntryDate(existedCustomer.getCustomerEntryDate());
        }

        if (customer.getContact() != null){
            existedCustomer.setContact(customer.getContact());
        } else {
            existedCustomer.setContact(existedCustomer.getContact());
        }

        if (customer.getEmail() != null){
            existedCustomer.setEmail(customer.getEmail());
        } else {
            existedCustomer.setEmail(existedCustomer.getEmail());
        }

        customerRepository.save(existedCustomer);
    }

}
