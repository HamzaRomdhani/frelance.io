package iteam.platform.freelancer.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUploadUtil {

    private static final String UPLOAD_DIR = "src/main/resources/static/assets/img/new/";
    private static final String UPLOAD_DIR_RESUME = "src/main/resources/static/assets/resume/";


    public String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;

        String originalName = file.getOriginalFilename();
        String fileExtension = "";
        if (originalName != null && originalName.contains(".")) {
            fileExtension = originalName.substring(originalName.lastIndexOf("."));
        }
        String savedFileName = UUID.randomUUID() + fileExtension;

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadPath.toString(), savedFileName);
        Files.write(path, bytes);

        return "/assets/img/new/" + savedFileName;
    }


    
    public String saveResume(MultipartFile resume) throws IOException {
        if (resume.isEmpty()) {
            throw new IllegalArgumentException("Please upload a valid PDF file");
        }

        if (!resume.getOriginalFilename().endsWith(".pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed.");
        }

        String originalName = resume.getOriginalFilename();
        String savedFileName = UUID.randomUUID() + ".pdf";

        Path uploadPath = Paths.get(UPLOAD_DIR_RESUME);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        byte[] bytes = resume.getBytes();
        Path path = Paths.get(uploadPath.toString(), savedFileName);
        Files.write(path, bytes);

        return "/assets/resume/" + savedFileName; // Relative path to access later
    }
}