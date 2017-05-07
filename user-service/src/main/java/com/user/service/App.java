package com.user.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.ProxyCachingConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.core.env.stack.StackResourceRegistry;
import org.springframework.cloud.aws.core.env.stack.config.StackResourceRegistryFactoryBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.user"})
@EnableCircuitBreaker
@EnableAutoConfiguration(exclude = {
        StackResourceRegistryFactoryBean.class,
        ContextStackAutoConfiguration.class,
        StackResourceRegistry.class,
        ProxyCachingConfiguration.class
})
public class App {
	
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
