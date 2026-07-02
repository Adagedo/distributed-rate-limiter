package code.adagedo.apigateway.rules;

import lombok.*;

@Data
public class RateLimit {
    private String unit;
    private Integer requestPerUnit;
}
