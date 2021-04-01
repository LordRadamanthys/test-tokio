package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.model.CustomerModel;
import com.example.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Customer>> find(@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        Page<Customer> customersPage = service.find(page);
        return ResponseEntity.ok().body(customersPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@NotNull @PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public Object createUser(@Valid @NotNull @RequestBody CustomerModel customer) {
        try {
            return service.create(customer);

        } catch (Exception e) {
            ResponseStatusException customer_not_found = new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage());
            return customer_not_found.getMessage();
        }
    }


    @PutMapping(value = "/{id}")
    public Object updateUser(@NotNull @PathVariable("id") long id, @RequestBody CustomerModel customer) {
        try {
            return service.update(id, customer);
        } catch (Exception e) {
            ResponseStatusException customer_not_found = new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage());
            return customer_not_found.getMessage();
        }
    }


    @DeleteMapping(value = "/{id}")
    public Object deleteUser(@NotNull @PathVariable("id") long id) {
        try {
            return service.delete(id);
        } catch (Exception e) {
            ResponseStatusException customer_not_found = new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    e.getMessage());
            return customer_not_found.getMessage();
        }
    }
}
