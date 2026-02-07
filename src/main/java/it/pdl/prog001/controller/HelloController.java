package it.pdl.prog001.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.pdl.prog001.hello.HelloResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class HelloController{

	@GetMapping("/hello")
	public Mono<ResponseEntity<HelloResponse>> hello(
		@RequestParam(defaultValue = "World") String name){

		log.debug("Ingresso endpoint GET /api/v1/hello, name={}", name);

		String requestId = UUID
			.randomUUID()
			.toString();

		HttpHeaders headers = new HttpHeaders();

		// Aggiungo l'header X-Request-Id alla risposta.
		// Lo uso per tracciare le richieste nei log.
		headers
			.add("X-Request-Id", requestId);

		return Mono
			.just(ResponseEntity
				.ok()
				.headers(headers)
				.body(new HelloResponse("Hello " + name)));
	}
}
