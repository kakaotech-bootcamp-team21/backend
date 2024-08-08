package kakaotech_bootcamp.team_21.coverletter_spring_project.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Kakao-Tech-Bootcamp-Team21-APIDoc")
                        .version("1.0.0")
                        .description("This is a Spring Boot RESTful service using OpenAPI 3."));
    }

    @Bean

    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/api/**")
                .build();
    }
}
