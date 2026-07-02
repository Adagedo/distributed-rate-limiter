package code.adagedo.apigateway.service;

public interface RateLimiterService {
    boolean isAllowed(String key);
}
