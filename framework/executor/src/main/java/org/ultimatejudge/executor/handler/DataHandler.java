package org.ultimatejudge.executor.handler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.ultimatejudge.executor.handler.model.response.DataResponse;

import java.io.File;
import java.nio.file.Paths;

import static org.ultimatejudge.executor.constant.API.DATA_UPLOAD;

@RestController
public class DataHandler {
    @PostMapping(DATA_UPLOAD)
    DataResponse dataUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return DataResponse.builder().status("Failed").build();
        }
        try {
            System.out.println(Paths.get("path/to/save/" + file.getOriginalFilename()));
            System.out.println(new File("path/to/save/" + file.getOriginalFilename()).getAbsoluteFile());
//            Files.write(Paths.get("path/to/save/" + file.getOriginalFilename()), file.getBytes());
            return DataResponse.builder().status("Success").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
