
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.mq")
public class MqApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(MqApplication.class, args);
		
	}

}
