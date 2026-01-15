package it.pdl.prog001;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Proj001ApplicationTest {

  @Autowired private WebTestClient webTestClient;

  @Test
  void contextLoads() {}

  @Test
  void testHelloEndpoint() {
    webTestClient
        .get()
        .uri("/api/v1/hello")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.message")
        .isEqualTo("Hello World");
  }
}
