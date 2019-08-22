package com.ming.springbootmetrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

@SpringBootApplication
public class SpringbootMetricsApplication {

	public static void main(String[] args) {
		RuntimeMXBean rtMB = ManagementFactory.getRuntimeMXBean();
		String rtName = rtMB.getName();

		SpringApplication.run(SpringbootMetricsApplication.class, args);
		System.out.println("======"+rtName);
	}

	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName){
		return registry -> registry.config().commonTags("application",applicationName);
	}

}
