package ml.memelau.catcher.client.spring.boot.autoconfigure;


import ml.memelau.catcher.client.Additioner;
import ml.memelau.catcher.client.CatcherClient;
import ml.memelau.catcher.client.UncaughtExceptionCatcher;
import ml.memelau.catcher.client.spring.CatcherWebInterceptor;
import ml.memelau.catcher.event.ErrorEvent;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;

import java.util.List;


/**
 * @author meme
 */
@Configuration
@ConditionalOnClass(CatcherClient.class)
@EnableConfigurationProperties(CatcherClientProperties.class)
@ConditionalOnProperty(prefix = CatcherClientProperties.PREFIX, name = "enabled", matchIfMissing = true)
@AutoConfigureAfter({
        WebMvcAutoConfiguration.class,
        WebFluxAutoConfiguration.class,
        KafkaAutoConfiguration.class
})
public class CatcherClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public HttpClient catcherHttpClient() {
        return HttpClient.create();
    }

    @Bean
    @ConditionalOnMissingBean
    public CatcherClient catcherClient(CatcherClientProperties properties, HttpClient catcherHttpClient) {
        return CatcherClient.builder()
                            .accessKey(properties.getAccessKey())
                            .endpoint(properties.getEndpoint())
                            .port(properties.getPort())
                            .httpClient(catcherHttpClient)
                            .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public UncaughtExceptionCatcher uncaughtExceptionCatcher(CatcherClientProperties properties, CatcherClient catcherClient) {
        return UncaughtExceptionCatcher.builder()
                                       .client(catcherClient)
                                       .appName(properties.getAppName())
                                       .env(properties.getEnv())
                                       .build();
    }

    @Configuration
    @ConditionalOnWebApplication
    @EnableConfigurationProperties(CatcherClientProperties.class)
    @AutoConfigureAfter(CatcherClientAutoConfiguration.class)
    static class CatcherWebConfiguration {

        @Bean
        public CatcherWebInterceptor catcherWebInterceptor(CatcherClientProperties properties, CatcherClient catcherClient, List<Additioner<? super ErrorEvent>> additioners) {
            return new CatcherWebInterceptor(properties.getEnv(), properties.getAppName(), catcherClient, additioners);
        }

    }

}
