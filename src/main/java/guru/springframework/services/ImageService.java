package guru.springframework.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void saveImageFile(Long id, MultipartFile file) throws IOException;
}
