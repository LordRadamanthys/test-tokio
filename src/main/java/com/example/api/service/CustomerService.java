package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.exceptions.ObjectNotFoundException;
import com.example.api.model.AddressModel;
import com.example.api.model.CustomerModel;
import com.example.api.repository.CustomerPagination;
import com.example.api.repository.CustomerRepository;
import com.example.api.utils.ValidatorsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerPagination repositoryPagination;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAll() {


        return repository.findAllByOrderByNameAsc();
    }

    public Page<Customer> find(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return repositoryPagination.findAll(pageable);
    }

    public Customer findById(Long id) {
        Optional<Customer> customer = repository.findById(id);
        return customer.orElseThrow(()-> new ObjectNotFoundException("Usuario não encontrado com este id"));
    }

    public Object create(CustomerModel customer) throws Exception {
        if (customer.getName().equals("") || customer.getName().contains(" "))
            throw new Exception("Digite nome e sobrenome");
        if (customer.getEmail().equals("") || !ValidatorsUtil.validateEmail(customer.getEmail()))
            throw new Exception("E-mail inválido");
        if (!ValidatorsUtil.validateCEP(customer.getCep())) throw new Exception("CEP inválido");

        ViaCep rest = new ViaCep();
        Address addressEntity = new Address();
        ArrayList<AddressModel> lisResponseCep = rest.get(customer.getCep());
        List<Address> list = new ArrayList<>();
        for (AddressModel cep : lisResponseCep) {
            addressEntity.setBairro(cep.getBairro());
            addressEntity.setCep(cep.getCep());
            addressEntity.setComplemento(cep.getComplemento());
            addressEntity.setDdd(cep.getDdd());
            addressEntity.setGia(cep.getGia());
            addressEntity.setIbge(cep.getIbge());
            addressEntity.setLocalidade(cep.getLocalidade());
            addressEntity.setLogradouro(cep.getLogradouro());
            addressEntity.setUf(cep.getUf());
            addressEntity.setSiafi(cep.getSiafi());
            list.add(addressEntity);
            addressEntity = new Address();
        }


        Customer customerEntity = new Customer();
        customerEntity.setName(customer.getName());
        customerEntity.setEmail(customer.getEmail());

        customerEntity.setAddress(list);
        try {
            repository.save(customerEntity);
            return new ResponseEntity<String>("Criado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Usuario ja existe", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> update(long id, CustomerModel customer) throws Exception {
        if (customer.getName().equals("") || customer.getName().contains(" "))
            throw new Exception("Digite nome e sobrenome");
        if (customer.getEmail().equals("") || !ValidatorsUtil.validateEmail(customer.getEmail()))
            throw new Exception("E-mail inválido");
        if (!ValidatorsUtil.validateCEP(customer.getCep())) throw new Exception("CEP inválido");

        return repository.findById(id)
                .map(c -> {
                    ViaCep rest = new ViaCep();
                    Address addressEntity = new Address();
                    ArrayList<AddressModel> lisResponseCep = rest.get(customer.getCep());
                    List<Address> list = new ArrayList<>();
                    for (AddressModel cep : lisResponseCep) {
                        addressEntity.setBairro(cep.getBairro());
                        addressEntity.setCep(cep.getCep());
                        addressEntity.setComplemento(cep.getComplemento());
                        addressEntity.setDdd(cep.getDdd());
                        addressEntity.setGia(cep.getGia());
                        addressEntity.setIbge(cep.getIbge());
                        addressEntity.setLocalidade(cep.getLocalidade());
                        addressEntity.setLogradouro(cep.getLogradouro());
                        addressEntity.setUf(cep.getUf());
                        addressEntity.setSiafi(cep.getSiafi());
                        list.add(addressEntity);
                        addressEntity = new Address();
                    }
                    c.setEmail(customer.getEmail());
                    c.setName(customer.getName());
                    c.setAddress(list);
                    repository.save(c);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.badRequest().body(new ResponseEntity<String>("Usuario não encontrado",
                        HttpStatus.BAD_REQUEST)));

    }

    public ResponseEntity<ResponseEntity<String>> delete(long id) throws Exception {
        if (id < 1) throw new Exception("invalid id");
        return repository.findById(id)
                .map(c -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().body(new ResponseEntity<String>("Usuario apagado",
                            HttpStatus.OK));
                }).orElse(ResponseEntity.badRequest().body(new ResponseEntity<String>("Usuario não encontrado",
                        HttpStatus.BAD_REQUEST)));

    }

}
