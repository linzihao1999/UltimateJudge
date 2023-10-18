package org.ultimatejudge.executor.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ultimatejudge.executor.constant.R;
import org.ultimatejudge.executor.constant.Status;
import org.ultimatejudge.executor.entity.CodeJudgeResult;
import org.ultimatejudge.executor.handler.model.request.CodeRequest;
import org.ultimatejudge.executor.handler.model.response.CodeResponse;
import org.ultimatejudge.executor.service.DockerService;
import org.ultimatejudge.executor.service.FileService;

import static org.ultimatejudge.executor.constant.API.*;

@Log4j2
@RestController
public class CodeHandler {
    FileService fileService;
    DockerService dockerService;

    @Autowired
    public CodeHandler(FileService fileService, DockerService dockerService) {
        this.fileService = fileService;
        this.dockerService = dockerService;
    }

    @PostMapping(CODE_UPLOAD)
    CodeResponse codeUpload(CodeRequest request) {
        if (fileService.saveFileTo(request.getFile(), request.getCodeId(), R.SOURCE_CODE_SAVE_PATH)) {
            return CodeResponse.builder().status(Status.SUCCEED.name()).build();
        }
        return CodeResponse.builder().status(Status.FAILED.name()).build();
    }

    @PostMapping(CODE_JUDGE)
    CodeResponse codeJudge(CodeRequest request) {
        dockerService.commitCodeJudgeJob(request.getCodeId(), request.getDataId());
    }

    @GetMapping(GET_JUDGE_RESULT)
    CodeResponse getJudgeResult(CodeRequest request) {
        CodeJudgeResult result = dockerService.getJudgeResult(request.getCodeId(), request.getDataId());
        return CodeResponse.builder().
    }
}
