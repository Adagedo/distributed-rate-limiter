package code.adagedo.apigateway.rules;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


import java.util.List;

@Data
@ConfigurationProperties(prefix = "api-gateway")
public class RateLimitingRules {
    private List<DomainRule> rules;
}
