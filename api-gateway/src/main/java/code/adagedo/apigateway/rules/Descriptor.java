package code.adagedo.apigateway.rules;

import lombok.*;

@Data
public class Descriptor {

    private String key;
    private String value;
    private RateLimit rateLimit;
}
