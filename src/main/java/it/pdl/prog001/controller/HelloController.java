package it.pdl.prog001.controller;

import it.pdl.prog001.dto.HelloRequest;
import it.pdl.prog001.dto.HelloResponse;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class HelloController{

	@GetMapping("/hello")
	public Mono<ResponseEntity<HelloResponse>> hello(
		@RequestParam(defaultValue = "World") String name){

		log.debug("Ingresso endpoint GET /api/v1/hello, name={}", name);

		HttpHeaders headers = new HttpHeaders();
		headers
			.add("X-Request-Id", UUID
				.randomUUID()
				.toString());

		return Mono
			.just(ResponseEntity
				.ok()
				.headers(headers)
				.body(new HelloResponse("Hello " + name)));
	}
}
