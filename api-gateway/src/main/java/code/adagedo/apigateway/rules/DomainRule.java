package code.adagedo.apigateway.rules;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
public class DomainRule {

    private String domain;
    private List<Descriptor> descriptors;
}
