package com.umoo.board.service;

import com.umoo.board.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    @Value("${custom.path.upload-path}")
    private String uploadPath;

    @Value("${custom.path.upload-images}")
    private String contextPath;

    public ModelAndView uploadImage(MultipartHttpServletRequest request) throws Exception{

        ModelAndView mav = new ModelAndView("jsonView");
        MultipartFile uploadedFile = request.getFile("upload");
        String path = uploadPath.replace("/", "\\");
        String originFileName = uploadedFile.getOriginalFilename();

        // split은 String[]의 하위클래스 이기 때문에 불필요한 60개의 배열을 생성한다는 답변이 있음
        String fileName = originFileName.substring(0, originFileName.indexOf("."));
        String ext = originFileName.substring(originFileName.indexOf("."));

        String newFileName = fileName + "_" + UUID.randomUUID() + ext;

//        String realPath = request.getServletContext().getRealPath("/");
//        String savePath = realPath + "upload/" + newFileName;
//        String uploadPath = "./upload/" + newFileName;

        File file = new File(path, newFileName);
        uploadedFile.transferTo(file);

        mav.addObject("uploaded", true);
        mav.addObject("url", contextPath.replace("**", newFileName));

        return mav;
    }
}
