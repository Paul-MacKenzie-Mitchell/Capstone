package learn.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        var encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("p@ssw0rd"));
        System.out.println(encoder.encode("admin"));
        SpringApplication.run(App.class, args);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

//$2a$10$TfBcez2cmXcIbx8dw8S3I.b7TAX/Ek2r1492ryukIZ7M9Mb5hD1eS
//        $2a$10$8q1ZzKAa2Mi9fEsQi1YQheM7pPCJNSjZT5au2JiyMn3qc4ENclDHu