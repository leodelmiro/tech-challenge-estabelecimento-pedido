package com.leodelmiro.estabelecimento.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    private final String devUrl = "localhost:8080";

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL para ambiente de Dev");

        Contact contact = new Contact();
        contact.setEmail("leodelmiroms@gmail.com");
        contact.setName("Leonardo Delmiro");
        contact.setUrl("https://github.com/leodelmiro");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Tech Challenge - Estabelecimento")
                .version("1.0")
                .contact(contact)
                .description("A API expõe endpoints para o Tech challenge de Estabelecimento de Pedidos de Alimentação (Fast food).")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}