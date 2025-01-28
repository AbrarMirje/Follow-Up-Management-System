package com.followup.service;

import com.followup.entity.Customer;

import java.util.List;

public interface ICustomerService {

    Customer addCustomer(Customer customer);

    Customer getCustomer(Long id);

    List<Customer> getCustomers();

    Boolean deleteCustomer(Long id);

    void updateCustomer(Customer customer, Long id);
}
