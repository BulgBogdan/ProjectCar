package projectCar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@org.springframework.boot.autoconfigure.SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }
}