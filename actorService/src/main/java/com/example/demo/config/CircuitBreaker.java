package com.example.demo.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration(proxyBeanMethods = false)
public class CircuitBreaker {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build())
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .build());
    }

//    public CircuitBreakerConfigCustomizer getCircuitBreakerConfig() {
//
//        return CircuitBreakerConfigCustomizer
//                .of("secondService", builder -> {
//                    builder.slidingWindowSize(5);
//                    builder.failureRateThreshold(3);
//                    builder.waitDurationInOpenState(Duration.ofSeconds(10));
//                    builder.slowCallDurationThreshold(Duration.ofSeconds(5));
//                    builder.slowCallRateThreshold(100);
//                    builder.permittedNumberOfCallsInHalfOpenState(2);
//                    builder.slidingWindowType(COUNT_BASED);
//                });
//    }
}