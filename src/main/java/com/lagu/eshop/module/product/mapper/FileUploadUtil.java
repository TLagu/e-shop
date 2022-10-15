package com.lagu.eshop.module.product.mapper;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * File upload util
 * @author Tomasz Łagowski
 * @version 1.0
 */
public class FileUploadUtil {

    public final static String PATH_CORE = "e:/praca/java/eshop/eshop/src/main/resources/static";

    /**
     * Save file
     * @since 1.0
     * @param uploadDir Upload directory
     * @param fileName File name
     * @param multipartFile MultipartFile
     * @throws IOException
     */
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(PATH_CORE + uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Błąd zapisu pliku: " + fileName, ioe);
        }
    }

}
