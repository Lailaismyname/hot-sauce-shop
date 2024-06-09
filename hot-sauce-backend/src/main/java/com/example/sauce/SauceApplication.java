package com.example.sauce;

import com.example.sauce.appconfiguration.CustomBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SauceApplication {

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(SauceApplication.class);
    application.setBanner(new CustomBanner());
    application.run(args);
  }
}
