package org.ultimatejudge.core.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodeJudgeEntity implements Serializable {
    @JsonProperty(value = "uid")
    private String uid;
    @JsonProperty(value = "code")
    private String code;
}
