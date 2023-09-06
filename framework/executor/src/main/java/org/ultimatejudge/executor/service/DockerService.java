package org.ultimatejudge.executor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.transport.DockerHttpClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.*;

@Log4j2
@Service
public class DockerService {
    private final DockerHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public DockerService(DockerHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public Info getDockerInfo() {
        try {
            buildImage();
        } catch (FileNotFoundException e) {
            log.error("File not found");
        }
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                                                                   .method(DockerHttpClient.Request.Method.GET)
                                                                   .path("/info")
                                                                   .build();
        try (DockerHttpClient.Response response = httpClient.execute(request)) {
            return objectMapper.readValue(response.getBody(), Info.class);
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }


    public void buildImage() throws FileNotFoundException {
        File file = new File("framework/executor/Dockerfile.tar.gz");
        log.info(file.getAbsolutePath());
        InputStream fileInputStream = new FileInputStream(file);
        DockerHttpClient.Request request = DockerHttpClient.Request.builder()
                                                                   .method(DockerHttpClient.Request.Method.POST)
                                                                   .body(fileInputStream)
                                                                   .path("/build?t=build_test")
                                                                   .build();
        try (DockerHttpClient.Response response = httpClient.execute(request)) {
            InputStream inputStream = response.getBody();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }
}
