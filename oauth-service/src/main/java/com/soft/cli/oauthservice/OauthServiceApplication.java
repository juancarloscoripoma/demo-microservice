package com.soft.cli.oauthservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableDiscoveryClient
@SessionAttributes("authorizationRequest")
@EnableFeignClients
@EnableResourceServer
public class OauthServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(OauthServiceApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(OauthServiceApplication.class);
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

