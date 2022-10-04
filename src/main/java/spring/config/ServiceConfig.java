package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"models.member.service", "models.file.service"})
public class ServiceConfig {

}
