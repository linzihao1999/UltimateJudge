package org.ultimatejudge.executor.config;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.ultimatejudge.executor.interceptor.LogInterceptor;

@Log4j2
@Data
@Component
@ConfigurationProperties(prefix = "ultimatejudge.framework.executor")
public class AppConfig implements WebMvcConfigurer {
    private String type;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
    }

    @Bean
    public DockerHttpClient provideLocalDockerHttpClient() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        log.info("Get docker host: " + config.getDockerHost());
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder().dockerHost(config.getDockerHost()).build();
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                                                                   .method(DockerHttpClient.Request.Method.GET)
                                                                   .path("/_ping")
                                                                   .build();
        try (DockerHttpClient.Response response = httpClient.execute(request)) {
            log.info("Docker connect with status: " + response.getStatusCode());
            return httpClient;
        } catch (Exception e) {
            log.error("Docker connect error", e);
            return null;
        }
    }
}
