
package com.example.clima.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.clima.service.ClimaService;

@RestController
public class ClimaController {
    
    private final ClimaService service;

    public ClimaController(ClimaService service) {
        this.service = service;
    }

    @GetMapping("/clima")
    public String consultarClimaBH(){
        return service.consultarClimaBH();
    }

    @GetMapping("/clima/{cidade}")
    public String consultarClima(@PathVariable String cidade){
        return service.consultarClima(cidade);
    }

}
