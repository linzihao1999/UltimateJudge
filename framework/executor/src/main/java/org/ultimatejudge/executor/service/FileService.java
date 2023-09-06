package org.ultimatejudge.executor.service;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.ultimatejudge.executor.constant.R;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
@Service
public class FileService {
    public boolean saveFileTo(@NonNull MultipartFile file, String name, String path) {
        try {
            File dir = new File(R.SOURCE_CODE_SAVE_PATH);
            if (!dir.exists()) dir.mkdirs();
            Files.write(Paths.get(path, name), file.getBytes());
            return true;
        } catch (IOException e) {
            log.error(e);
            return false;
        }
    }
}
