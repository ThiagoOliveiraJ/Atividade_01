package com.example.clima.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClimaService {
    private static final String BASE_URL_BH = "https://api.open-meteo.com/v1/forecast?latitude=-19.555&longitude=-49.555&hourly=temperature_2m";
    private static final String BASE_URL = "https://geocoding-api.open-meteo.com/v1/search?name=";

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

    private String normalizeParameter(String cidade){
        cidade = cidade.replace("-", " ");
        return cidade;
    }

    public String consultarClimaBH() {
        return consultarURL(BASE_URL_BH);
    }
    public String consultarClima(String cidade) {
        return consultarURL(BASE_URL + normalizeParameter(cidade) + "&count=1&language=pt&format=json");
    }
    
}
