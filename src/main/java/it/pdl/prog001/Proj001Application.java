package it.pdl.prog001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Proj001Application{

	public static void main(
		String[] args){

		SpringApplication
			.run(Proj001Application.class, args);
	}
}
