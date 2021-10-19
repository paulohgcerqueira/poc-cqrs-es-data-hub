package br.com.vr.orderservice.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.vr.orderservice"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo(
                "Order Service - CQRS e Event Sourcing para Data Aggregator",
                "PoC dedicada a validar o uso do CQRS e Event Sourcing para Data Aggregator usando o Axon em arquitetura baseada em eventos.",
                "1.0.0",
                "Terms of Service",
                new Contact("Paulo Cerqueira", "vr.com.br", "paulohgcerqueira@vr.com.br"),
                "",
                "",
                Collections.emptyList());
    }

}