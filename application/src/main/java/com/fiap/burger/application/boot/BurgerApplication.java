package com.fiap.burger.application.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fiap.burger")
public class BurgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BurgerApplication.class, args);
	}

}
