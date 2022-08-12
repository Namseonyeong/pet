package com.ezen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{

	@Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		
			
	//	registry.addResourceHandler("static/img_file/**").addResourceLocations("fils:///D:/pet/pet_1/src/main/resources/static/img_file/");
		
		registry.addResourceHandler("/img_file/**").addResourceLocations("fils://D:/pet/pet_1/src/main/resources/static/img_file/");
	}

}
