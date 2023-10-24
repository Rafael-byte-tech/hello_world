package br.ifes.dw.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@OpenAPIDefinition(info = @Info(title = "Project Tequila Sunset", version = "1.0", description = "YEAH!"))
public class HelloworldApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(HelloworldApplication.class, args);
  }
}