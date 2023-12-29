package com.example.springbootrestapidb;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootRestApiDbApplication {
    @Bean       //We create a Bean Class so that we don't have to create a object of Modelmapper every time we use it.
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestApiDbApplication.class, args);
    }

}
