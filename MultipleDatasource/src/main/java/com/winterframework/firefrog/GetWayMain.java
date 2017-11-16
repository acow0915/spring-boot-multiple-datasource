package com.winterframework.firefrog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * main
 *
 */
@SpringBootApplication
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
