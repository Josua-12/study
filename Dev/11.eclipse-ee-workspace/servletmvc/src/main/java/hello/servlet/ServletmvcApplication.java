package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ServletmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletmvcApplication.class, args);
	}

}
