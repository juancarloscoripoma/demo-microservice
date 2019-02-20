package com.soft.cli.eurekaregistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class EurekaRegistryApplication {

	private static final Logger log = LoggerFactory.getLogger(EurekaRegistryApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(EurekaRegistryApplication.class);
		Environment env = app.run(args).getEnvironment();

		log.info("\n----------------------------------------------------------\n\t" +
						"TSoft.Demo CLIENT Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\thttp://localhost:{}\n\t" +
						"External: \thttp://{}:{}\n\t" +
						"Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"),
				env.getActiveProfiles());
	}

}

