package com.allways;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//aws log error 나오는거 막기위해서
@SpringBootApplication(
		exclude = {
				org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
				org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
				org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
		}
)
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableFeignClients
public class AllwaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllwaysApplication.class, args);
	}

}
