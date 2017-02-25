package com.charlie.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.charlie.entity.Customer;

public interface CustomerDao extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstName(String firstName);
}