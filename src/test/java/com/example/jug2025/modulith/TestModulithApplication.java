package com.example.jug2025.modulith;

import org.springframework.boot.SpringApplication;

public class TestModulithApplication {

	public static void main(String[] args) {
		SpringApplication.from(ModulithApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
