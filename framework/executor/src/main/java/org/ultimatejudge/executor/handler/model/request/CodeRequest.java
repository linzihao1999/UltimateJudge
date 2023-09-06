package org.ultimatejudge.executor.handler.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodeRequest implements Serializable {
    @JsonProperty("codeId")
    private String codeId;

    @JsonProperty("dataId")
    private String dataId;

    @JsonProperty("file")
    private MultipartFile file;
}
