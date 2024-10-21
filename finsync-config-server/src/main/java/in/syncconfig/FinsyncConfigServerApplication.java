package in.syncconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FinsyncConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinsyncConfigServerApplication.class, args);
	}

}
