package code.adagedo.ratelimiterservice.service;

import code.adagedo.rate_limiter.RateLimitRequest;
import code.adagedo.rate_limiter.RateLimitResponse;
import code.adagedo.rate_limiter.RateLimitServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class RateLimitServiceImpl extends RateLimitServiceGrpc.RateLimitServiceImplBase {
    @Override
    public void validate(RateLimitRequest request, StreamObserver<RateLimitResponse> responseObserver) {

        RateLimitResponse response = RateLimitResponse.newBuilder()
                .setAllowed(true)
                .setTokenLeft(10)
                .setResetAfterMs(System.currentTimeMillis() + 60000)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
