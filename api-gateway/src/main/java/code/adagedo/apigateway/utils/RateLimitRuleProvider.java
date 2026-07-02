package code.adagedo.apigateway.utils;

import code.adagedo.apigateway.rules.Descriptor;
import code.adagedo.apigateway.rules.DomainRule;
import code.adagedo.apigateway.rules.RateLimitingRules;
import code.adagedo.apigateway.rules.RateLimit;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class RateLimitRuleProvider {

    private final RateLimitingRules rateLimitingRules;

    private volatile Map<String, RateLimit> lookup = Collections.emptyMap();

    @PostConstruct
    public void initialize() {
        rebuildLookup();

    }

    @EventListener(RefreshScopeRefreshedEvent.class)
    public void refresh() {
        rebuildLookup();
    }

    public RateLimit regetRules(String domain, String endpoint){
        return lookup.get(buildKey(domain, endpoint));
    }

    private String buildKey(String domain, String endpoint){
        return domain + ":" + endpoint;
    }

    private void rebuildLookup(){
        Map<String, RateLimit> map = new HashMap<>();
        log.info("logging rules: {}", rateLimitingRules);
        if(rateLimitingRules == null || rateLimitingRules.getRules() == null){
            log.info("Rate limiting Rules have not been loaded from the config server yet");
            this.lookup = Collections.emptyMap();
            return;
        }
        for (DomainRule domainRule: rateLimitingRules.getRules()){
            for (Descriptor descriptor: domainRule.getDescriptors()){
                String key = buildKey(domainRule.getDomain(), descriptor.getValue());
                map.put(key, descriptor.getRateLimit());
            }
        }
        lookup = Map.copyOf(map);
    }
}
