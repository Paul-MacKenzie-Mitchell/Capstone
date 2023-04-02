package learn.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
//        var encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("p@ssw0rd"));
//        System.out.println(encoder.encode("admin"));
        SpringApplication.run(App.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        // Configure CORS globally versus
        // controller-by-controller or method-by-method.
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("DELETE", "GET", "POST", "PUT", "OPTIONS")
                        .allowedOrigins("*");
            }
        };
    }

}
