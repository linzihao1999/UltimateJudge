package org.ultimatejudge.executor.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodeJudgeResult implements Serializable {
    @JsonProperty("codeId")
    private final String codeId;

    @JsonProperty("dataID")
    private final String dataId;

    @JsonProperty("acceptedDataList")
    private final
}
