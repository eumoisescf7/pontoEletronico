package br.com.ilia.pontoeletronico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration.class)
@SpringBootApplication
@Configuration
public class PontoeletronicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontoeletronicoApplication.class, args);
	}

}
