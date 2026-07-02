package code.adagedo.apigateway.config;

import code.adagedo.apigateway.rules.RateLimitingRules;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api-gateway")
public class RateLimitingRulesConfiguration {

    @Bean
    public RateLimitingRules rateLimitingRules() {
        return new RateLimitingRules();
    }
}
