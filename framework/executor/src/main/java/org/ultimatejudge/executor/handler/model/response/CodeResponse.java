package org.ultimatejudge.executor.handler.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodeResponse implements Serializable {
    @JsonProperty("status")
    private final String status;
}
