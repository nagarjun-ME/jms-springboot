package com.ibmmq.jms.spring.jmsspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class JmsSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsSpringBootApplication.class, args);
	}

}
