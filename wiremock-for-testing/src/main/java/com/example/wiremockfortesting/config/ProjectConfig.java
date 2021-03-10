package com.example.wiremockfortesting.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.wiremockfortesting.proxy")
public class ProjectConfig {
}
