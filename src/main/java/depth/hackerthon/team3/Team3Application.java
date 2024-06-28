package depth.hackerthon.team3;

import depth.hackerthon.team3.global.config.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:application-database.yml" }, factory = YamlPropertySourceFactory.class)
public class Team3Application {

	public static void main(String[] args) {
		SpringApplication.run(Team3Application.class, args);
	}

}
