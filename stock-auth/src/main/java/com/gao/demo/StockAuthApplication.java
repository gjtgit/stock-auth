package com.gao.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class StockAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAuthApplication.class, args);
	}

	//can use the CorsFilter defined in Zuul,so no need to define here
	//@Bean
//    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);  
//        config.addAllowedOrigin("*"); 
//        config.addAllowedHeader("*"); 
//        config.setMaxAge(18000L);
//        config.addAllowedMethod("*"); 
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET"); 
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PATCH");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
}
