package com.example.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

public class CustomerModel {
    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    @NotEmpty(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;


    @NotEmpty(message = "Lista de CEP não pode ser vazia")
    private ArrayList<String> cep;

    public ArrayList<String> getCep() {
        return cep;
    }

    public void setCep(ArrayList<String> cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
