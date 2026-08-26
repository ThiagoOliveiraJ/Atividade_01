package com.example.clima.dto;

public record ClimaResponse( 
    String Cidade,
    String estado,
    double temperaturaAtual,
    Integer umidade,
    double velocidadeVento,
    Integer direcaoVento,
    double temperaturaMaxima,
    double temperaturaMinima,
    String dataConsulta
){}
