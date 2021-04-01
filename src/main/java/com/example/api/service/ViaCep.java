package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.model.AddressModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class ViaCep {

    public ArrayList<AddressModel> get(ArrayList<String> cep){
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<AddressModel> list = new ArrayList<>();
        for (String v : cep) {
            String url = "https://viacep.com.br/ws/"+v+"/json/";
            AddressModel response = restTemplate.getForObject(url, AddressModel.class);
            list.add(response);
        }

        return list;
    }

    public AddressModel get(String cep){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/"+cep+"/json/";
        AddressModel response
                = restTemplate.getForObject(url, AddressModel.class);
        return response;
    }
}
