package it.pdl.prog001;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Proj001ApplicationTest{

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads(){

	}

	@Test
	void testHelloEndpoint(){

		webTestClient
			.get()
			.uri("/api/v1/hello")
			.exchange()
			.expectStatus()
			.isOk()
			.expectHeader()
			.exists("X-Request-Id")
			.expectBody()
			.jsonPath("$.message")
			.isEqualTo("Hello World");
	}

	@Test
	void testHelloEndpointWithName(){

		webTestClient
			.get()
			.uri("/api/v1/hello?name=Mario")
			.exchange()
			.expectStatus()
			.isOk()
			.expectHeader()
			.exists("X-Request-Id")
			.expectBody()
			.jsonPath("$.message")
			.isEqualTo("Hello Mario");
	}

	@Test
	void testCreateHello(){

		webTestClient
			.post()
			.uri("/api/v1/hello")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(Map.of("name", "Luigi"))
			.exchange()
			.expectStatus()
			.isCreated()
			.expectHeader()
			.valueMatches("Location", "/api/v1/hello\\?name=Luigi")
			.expectBody()
			.jsonPath("$.message")
			.isEqualTo("Hello Luigi");
	}
}
