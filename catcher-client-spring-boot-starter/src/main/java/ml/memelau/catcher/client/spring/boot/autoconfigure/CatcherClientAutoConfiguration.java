package ml.memelau.catcher.client.spring.boot.autoconfigure;


import ml.memelau.catcher.client.Additioner;
import ml.memelau.catcher.client.CatcherClient;
import ml.memelau.catcher.client.ErrorEventFactory;
import ml.memelau.catcher.client.UncaughtExceptionCatcher;
import ml.memelau.catcher.client.servlet.CatcherFilter;
import ml.memelau.catcher.client.spring.CatcherExceptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import reactor.netty.http.client.HttpClient;

import javax.servlet.DispatcherType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * @author meme
 */
@Configuration
@ConditionalOnClass(CatcherClient.class)
@EnableConfigurationProperties(CatcherClientProperties.class)
@ConditionalOnProperty(prefix = CatcherClientProperties.PREFIX, name = "enabled", matchIfMissing = true)
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
    public ErrorEventFactory errorEventFactory(CatcherClientProperties properties) {
        return new ErrorEventFactory(properties.getEnv(), properties.getAppName());
    }

    @Bean
    @ConditionalOnMissingBean
    public UncaughtExceptionCatcher uncaughtExceptionCatcher(ErrorEventFactory errorEventFactory, CatcherClient catcherClient, @Autowired(required = false) List<Additioner> additioners) {
        return UncaughtExceptionCatcher.builder()
                                       .client(catcherClient)
                                       .errorEventFactory(errorEventFactory)
                                       .additioners(additioners)
                                       .build();
    }

    @Configuration
    @ConditionalOnWebApplication
    @EnableConfigurationProperties(CatcherClientProperties.class)
    @AutoConfigureAfter(AdditionerConfiguration.class)
    static class CatcherWebConfiguration {

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Bean
        @ConditionalOnMissingBean
        public FilterRegistrationBean catcherFilter() {
            //兼容spring-boot 1
            final FilterRegistrationBean registration = new FilterRegistrationBean(new CatcherFilter());
            registration.setName("catcherFilter");
            registration.setDispatcherTypes(DispatcherType.ASYNC, DispatcherType.REQUEST);
            registration.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
            return registration;
        }

        @Bean
        public CatcherExceptionResolver catcherExceptionResolver(ErrorEventFactory errorEventFactory, CatcherClient catcherClient, @Autowired(required = false) List<Additioner> additioners) {
            return new CatcherExceptionResolver(errorEventFactory, catcherClient, Optional.ofNullable(additioners)
                                                                                          .orElseGet(Collections::emptyList));
        }

    }

    @Configuration
    @ConditionalOnWebApplication
    @EnableConfigurationProperties(CatcherClientProperties.class)
    @AutoConfigureAfter(CatcherClientAutoConfiguration.class)
    static class AdditionerConfiguration {


    }

}
