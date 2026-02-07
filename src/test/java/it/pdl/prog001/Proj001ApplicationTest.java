package it.pdl.prog001;

import static java.util.Objects.requireNonNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Proj001ApplicationTest{

	private final String messageJsonPath = "$.message";

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads(){

		log.info("contextLoads test started");

	}

	@Test
	void testHelloEndpoint(){

		log.info("Testing GET /api/v1/hello");

		webTestClient
			.get()
			.uri("/api/v1/hello")
			.exchange()
			.expectStatus()
			.isOk()
			.expectHeader()
			.exists("X-Request-Id")
			.expectBody()
			.jsonPath(messageJsonPath)
			.isEqualTo("Hello World");

		log.info("Completed GET /api/v1/hello");
	}

	@Test
	void testHelloEndpointWithName(){

		log.info("Testing GET /api/v1/hello?name=Mario");

		webTestClient
			.get()
			.uri("/api/v1/hello?name=Mario")
			.exchange()
			.expectStatus()
			.isOk()
			.expectHeader()
			.exists("X-Request-Id")
			.expectBody()
			.jsonPath(messageJsonPath)
			.isEqualTo("Hello Mario");
		log.info("Completed GET /api/v1/hello?name=Mario");
	}

	@Test
	void testCreateHello(){

		log.info("Testing POST /api/v1/hello with name Luigi");

		webTestClient
			.post()
			.uri("/api/v1/hello")
			.contentType(requireNonNull(APPLICATION_JSON))
			.bodyValue(Map.of("name", "Luigi"))
			.exchange()
			.expectStatus()
			.isCreated()
			.expectHeader()
			.valueMatches("Location", "/api/v1/hello\\?name=Luigi")
			.expectBody()
			.jsonPath(messageJsonPath)
			.isEqualTo("Hello Luigi");
		log.info("Completed POST /api/v1/hello with name Luigi");
	}
}
