package code.adagedo.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(
                        predicateSpec ->
                                predicateSpec
                                        .path("/api/v1/orders/**").uri("lb://SERVICE-TWO")
                )
                .route(
                        predicateSpec ->
                                predicateSpec
                                        .path("/api/v1/posts/**").uri("lb://SERVICE-ONE")
                )
                .build();
    }
}
