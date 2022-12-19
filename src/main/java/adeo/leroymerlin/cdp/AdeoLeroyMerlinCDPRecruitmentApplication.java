package adeo.leroymerlin.cdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@Configuration
@ComponentScan
@EnableTransactionManagement
public class AdeoLeroyMerlinCDPRecruitmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdeoLeroyMerlinCDPRecruitmentApplication.class, args);
	}
}
