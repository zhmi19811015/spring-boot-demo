/**
 * @Project spring-boot-demo
 * @Package com.ming.controller
 * @Description: TODO
 * @author zhangming
 * @date 2019/2/28 15:43
 * @version V1.0
 */
package com.ming.springbootfastdfs.controller;

import com.ming.springbootfastdfs.fastdfs.FastDFSClient;
import com.ming.springbootfastdfs.fastdfs.FastDFSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class UploadController {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception{
        return saveFile(file);
    }

    @PostMapping("/singleFileUpload") //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            String path = saveFile(file);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path",
                    "file path url '" + path + "'");
        } catch (Exception e) {
            logger.error("upload file failed", e);
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world ~");
    }

    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    private String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            //upload to fastdfs
            fileAbsolutePath = FastDFSClient.upload(file);
        } catch (Exception e) {
            logger.error("upload file Exception!", e);
        }
        if (fileAbsolutePath == null) {
            logger.error("upload file failed,please upload again!");
        }
        String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        return path;
    }
}
