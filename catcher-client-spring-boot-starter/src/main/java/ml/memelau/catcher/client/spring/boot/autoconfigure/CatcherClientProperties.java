package ml.memelau.catcher.client.spring.boot.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import ml.memelau.catcher.client.Additioner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author meme
 */
@Getter
@Setter
@ConfigurationProperties(CatcherClientProperties.PREFIX)
public class CatcherClientProperties {

    public static final String PREFIX = "catcher.client";

    public List<Class<Additioner>> additioners;

    private String endpoint;

    private int port;

    private String accessKey;

    @Value("${spring.profiles.active}")
    private String env;

    @Value("${spring.application.name}")
    private String appName;


}
