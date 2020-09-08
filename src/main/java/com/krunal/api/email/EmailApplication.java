package com.krunal.api.email;

import com.krunal.api.email.service.JiraService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.logging.Logger;

@SpringBootApplication
public class EmailApplication {

	private static final Logger LOG = (Logger.getLogger(EmailApplication.class.getName()));

	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
		LOG.info("Micro service is up now...");
	}
}
