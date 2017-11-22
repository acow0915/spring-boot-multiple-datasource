package com.winterframework.firefrog;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * main
 *
 */
@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class GetWayMain extends SpringBootServletInitializer 
{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GetWayMain.class);
    }
	 
    public static void main( String[] args )
    {
        SpringApplication.run(GetWayMain.class, args);
    }
}
