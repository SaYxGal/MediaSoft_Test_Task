package com.task.productWarehouse.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${openapi.url:http://localhost:8080}")
    private String url;
    @Bean
    public OpenAPI myOpenAPI(){
        Server server = new Server();
        server.setUrl(url);
        server.setDescription("Server URL in environment");
        Info info = new Info()
                .title("Mediasoft product warehouse API")
                .version("1.0")
                .description("This API exposes endpoints to manage products on warehouse.");
        return new OpenAPI().info(info).servers(List.of(server));
    }
}
