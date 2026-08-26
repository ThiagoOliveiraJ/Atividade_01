package com.example.clima.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClimaService {
    private static final String BASE_URL_BH = "xxxx";
    private static final String BASE_URL = "xxxx";

    private String consultarURL(String apiUrl){
        String dados = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dados = responseEntity.getBody();
        } else {
            dados = "Falha ao obter dados. Código de status: " + responseEntity.getStatusCode();
        }
        return dados;
    }

    public String consultarClimaBH() {
        return consultarURL(BASE_URL_BH);
    }
    public String consultarClima(String cidade) {
        return consultarURL(BASE_URL + cidade);
    }
    
}
