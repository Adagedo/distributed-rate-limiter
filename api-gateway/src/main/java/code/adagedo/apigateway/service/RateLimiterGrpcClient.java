package code.adagedo.apigateway.service;

import code.adagedo.rate_limiter.*;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RateLimiterGrpcClient {
    private final RateLimitServiceGrpc.RateLimitServiceBlockingStub rateLimitServiceBlockingStub;

    public RateLimiterGrpcClient(RateLimitServiceGrpc.RateLimitServiceBlockingStub rateLimitServiceBlockingStub) {
        this.rateLimitServiceBlockingStub = rateLimitServiceBlockingStub;
    }

    public RateLimitResponse rateLimitResponse(String domain, String key, String value){

        Entry entry = Entry.newBuilder().setKey(key).setValue(value).build();
        RateLimitDescriptor descriptor = RateLimitDescriptor.newBuilder().addEntries(entry).build();

        RateLimitRequest request = RateLimitRequest.newBuilder().setDomain(domain).addDescriptors(descriptor).build();
        try{
            log.info("Metadata sending to Rate Limiting service: {}", request);
            return this.rateLimitServiceBlockingStub.validate(request);
        }catch (StatusRuntimeException exception){
            log.error("Error sending Metadata to Rate Limiting service: {}", exception.getCause().getMessage());
            return RateLimitResponse.newBuilder()
                    .setAllowed(false)
                    .setTokenLeft(0)
                    .setResetAfterMs(System.currentTimeMillis())
                    .build();
        }
    }
}
