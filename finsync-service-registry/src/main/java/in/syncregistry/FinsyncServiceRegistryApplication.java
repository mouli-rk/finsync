package in.syncregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FinsyncServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinsyncServiceRegistryApplication.class, args);
	}

}
