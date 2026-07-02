package code.adagedo.apigateway.config;

import code.adagedo.apigateway.filters.CustomRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    private final CustomRateLimiterGatewayFilterFactory customRateLimiterGatewayFilterFactory;

    public ApiGatewayConfiguration(CustomRateLimiterGatewayFilterFactory customRateLimiterGatewayFilterFactory) {
        this.customRateLimiterGatewayFilterFactory = customRateLimiterGatewayFilterFactory;
    }

    @Bean
    public RouteLocator gatewayRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(
                        predicateSpec ->
                                predicateSpec
                                        .path("/api/v1/orders/**")
                                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                                        .filters(customRateLimiterGatewayFilterFactory
                                                                .apply(config -> config.setRouteId("orders")))

                                                )
                                        .uri("lb://SERVICE-TWO")

                )
                .route(
                        predicateSpec ->
                                predicateSpec
                                        .path("/api/v1/posts/**")
                                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                                .filters(customRateLimiterGatewayFilterFactory
                                                        .apply(config -> config.setRouteId("posts"))))
                                        .uri("lb://SERVICE-ONE")
                )
                .build();
    }
}
