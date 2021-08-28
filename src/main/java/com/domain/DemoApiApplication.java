package com.domain;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiApplication.class, args);
	}

	// bin untuk liblary model mapper
	// kita panggil model mappernya di supplier
	@Bean //supaya bisa kita inject dengan autowired jadi kita kasih bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
