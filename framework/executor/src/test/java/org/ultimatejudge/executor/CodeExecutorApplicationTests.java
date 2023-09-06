package org.ultimatejudge.executor;

import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ultimatejudge.executor.config.AppConfig;
import org.ultimatejudge.executor.service.DockerService;

import java.nio.file.Paths;

@Log4j2
@SpringBootTest
class CodeExecutorApplicationTests {
    @Test
    void test() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        System.out.println(config.getDockerHost());
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder().dockerHost(config.getDockerHost()).build();
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                                                                   .method(DockerHttpClient.Request.Method.GET)
                                                                   .path("/_ping")
                                                                   .build();

        try (DockerHttpClient.Response response = httpClient.execute(request)) {
            System.out.println(response.getStatusCode());
            System.out.println(response.getHeader("API-Version"));
        }
    }

    @Autowired
    public AppConfig appConfig;
    @Autowired
    public DockerService dockerService;

    @Test
    void test2() {
        System.out.println(Paths.get("./hi", "hello").toAbsolutePath());
    }
}
