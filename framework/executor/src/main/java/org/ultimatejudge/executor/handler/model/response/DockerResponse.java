package org.ultimatejudge.executor.handler.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dockerjava.api.model.Info;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DockerResponse implements Serializable {
    @JsonProperty(value = "info")
    private Info info;
}
