package com.example.api.repository;

import com.example.api.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPagination extends PagingAndSortingRepository<Customer, Integer> {

    Page<Customer> findAll(Pageable pageable);
}
