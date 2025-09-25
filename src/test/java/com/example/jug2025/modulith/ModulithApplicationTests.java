package com.example.jug2025.modulith;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ModulithApplicationTests {

	@Test
	void contextLoads() {
	}

}
