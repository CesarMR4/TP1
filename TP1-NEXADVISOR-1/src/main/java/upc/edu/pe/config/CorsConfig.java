package upc.edu.pe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") 
                .allowedOrigins("http://localhost:4200",  "http://nexadvisor-frontend.s3-website-us-east-1.amazonaws.com",
                		"https://nexadvisor-frontend.s3-website-us-east-1.amazonaws.com","https://d3j7fzwpgt0hl7.cloudfront.net","d3j7fzwpgt0hl7.cloudfront.net") 
                
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}