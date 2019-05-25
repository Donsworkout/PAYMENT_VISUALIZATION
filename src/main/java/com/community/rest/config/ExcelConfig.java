package com.community.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ComponentScan(basePackages = { "com.community.rest.*" })
@PropertySource("classpath:config.properties")
public class ExcelConfig {
	
	@Value("${excel.savePath}")
	private String savePath;
}
