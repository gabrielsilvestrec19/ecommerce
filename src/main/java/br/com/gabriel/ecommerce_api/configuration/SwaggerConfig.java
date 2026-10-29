package br.com.gabriel.ecommerce_api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce API")
                        .version("v1.0.0")
                        .description("Olá! Boas-vindas à documentação da minha API de E-commerce.\n\n" +
                                "Desenvolvi este projeto para colocar em prática conceitos sólidos de backend em Java. " +
                                "A ideia aqui foi construir o 'motor' de uma loja virtual, lidando com tudo o que acontece nos bastidores de uma compra.\n\n" +
                                "### O que você pode testar aqui:\n" +
                                "- **Catálogo:** Organização de categorias e controle de estoque de produtos.\n" +
                                "- **Clientes:** Cadastro e gerenciamento de perfis.\n" +
                                "- **Vendas:** Fluxo de criação de pedidos e atualização de status de compra.\n\n" +
                                "### Por baixo do capô:\n" +
                                "A API roda em **Java 25** com **Spring Boot 4.1.1**, conectando a um banco **MySQL** via Hibernate. " +
                                "Apliquei o padrão de **DTOs com Mappers** para isolar as entidades do banco de dados e garantir segurança no tráfego de informações.")
                        .contact(new Contact()
                                .name("Gabriel")
                                .url("https://github.com/gabrielsilvestrec19")
                                .email("gabrielsilvestrecoelho2@gmail.com")));
    }
}
