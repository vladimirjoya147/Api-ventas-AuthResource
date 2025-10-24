package com.amaru.ventas_amaru.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

@SpringBootApplication
=======
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
>>>>>>> 48489ff (desacoplando usuarios)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
