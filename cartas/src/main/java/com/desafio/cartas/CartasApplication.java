package com.desafio.cartas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "CartasApplication", version = "0.1.0", description = "Api para jogar carteado."))
@EnableFeignClients
public class CartasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartasApplication.class, args);
	}

}
