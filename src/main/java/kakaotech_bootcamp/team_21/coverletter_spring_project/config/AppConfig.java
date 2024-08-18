package kakaotech_bootcamp.team_21.coverletter_spring_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "kakaotech_bootcamp.team_21.coverletter_spring_project.repository")
public class AppConfig {
}
