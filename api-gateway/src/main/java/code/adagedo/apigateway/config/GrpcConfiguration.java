package code.adagedo.apigateway.config;

import code.adagedo.rate_limiter.RateLimitServiceGrpc;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.ImportGrpcClients;

@Configuration
@ImportGrpcClients(
        target = "rate-limiter-server",
        types = RateLimitServiceGrpc.RateLimitServiceBlockingStub.class
)
public class GrpcConfiguration {
}
