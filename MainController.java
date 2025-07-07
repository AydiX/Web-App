package com.example.imageupload;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final String uploadDir = System.getProperty("user.dir") + "/uploads";

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {

        String clientIp = request.getRemoteAddr();

        if (!clientIp.equals("20.218.226.24")) {
            return "accses-denied";
        }


        File folder = new File(uploadDir);
        if (!folder.exists()){
           boolean created = folder.mkdirs();
           if (!created) {
               throw new RuntimeException("Failed create upload directory");
           }
        }

        List<String> files = Arrays.stream(folder.listFiles())
                .map(File::getName)
                .collect(Collectors.toList());

        model.addAttribute("files", files);
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();

            if (!filename.toLowerCase().endsWith(".png")) {
                throw new IOException("Only PNG files are allowed!");
            }

            if (filename != null && filename.toLowerCase().endsWith(".png")) {
                File folder = new File(uploadDir);
                if (!folder.exists()) folder.mkdirs();

                File saveFile = new File(uploadDir + "/" + filename);
                file.transferTo(saveFile);
            }

        }
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteFile(@RequestParam("filename") String filename) {
        File file = new File(uploadDir + "/" + filename);
        if (file.exists()) {
            file.delete();
        }
        return "redirect:/";
    }
}

