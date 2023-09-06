package org.ultimatejudge.executor.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ultimatejudge.executor.handler.model.response.DockerResponse;
import org.ultimatejudge.executor.service.DockerService;

import static org.ultimatejudge.executor.constant.API.DOCKER_INFO;

@RestController
public class DockerHandler {
    private final DockerService dockerService;

    @Autowired
    public DockerHandler(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping(DOCKER_INFO)
    DockerResponse dockerInfo() {
        return DockerResponse.builder().info(dockerService.getDockerInfo()).build();
    }
}
