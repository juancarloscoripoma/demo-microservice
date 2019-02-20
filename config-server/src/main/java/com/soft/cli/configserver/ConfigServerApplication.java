package com.soft.cli.configserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

	private static final Logger log = LoggerFactory.getLogger(ConfigServerApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(ConfigServerApplication.class);
		Environment environment = app.run(args).getEnvironment();

		log.info("\n----------------------------------------------------------\n\t" +
						"TSoft.Demo CLIENT Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\thttp://localhost:{}\n\t" +
						"External: \thttp://{}:{}\n\t" +
						"Profile(s): \t{}\n----------------------------------------------------------",
				environment.getProperty("spring.application.name"),
				environment.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				environment.getProperty("server.port"),
				environment.getActiveProfiles());
	}

}

