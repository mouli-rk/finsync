package in.syncuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class FinsyncUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinsyncUserServiceApplication.class, args);
	}

}
