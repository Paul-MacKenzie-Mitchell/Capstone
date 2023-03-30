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
//        System.out.println(encoder.encode("p@ssw0rd"));
//        System.out.println(encoder.encode("admin"));
        SpringApplication.run(App.class, args);
    }

}
