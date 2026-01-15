package it.pdl.prog001.controller;

import it.pdl.prog001.dto.HelloResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class HelloController {

  @GetMapping("/hello")
  public Mono<HelloResponse> hello() {
    log.debug("Ingresso endpoint GET /api/v1/hello");
    return Mono.just(new HelloResponse("Hello World"));
  }
}
