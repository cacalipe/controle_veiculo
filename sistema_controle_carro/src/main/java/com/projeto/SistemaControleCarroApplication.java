package com.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SistemaControleCarroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaControleCarroApplication.class, args);
	}

}
