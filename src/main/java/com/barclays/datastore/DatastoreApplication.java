package com.barclays.datastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatastoreApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DatastoreApplication.class, args);
	}


}
