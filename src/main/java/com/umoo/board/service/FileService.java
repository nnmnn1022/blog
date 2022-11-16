package com.umoo.board.service;

import com.umoo.board.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    @Value("${custom.path.files}")
    private String uploadPath;

    @Value("${custom.path.upload-images}")
    private String contextPath;

    public ModelAndView uploadImage(MultipartHttpServletRequest request) throws Exception{

        ModelAndView mav = new ModelAndView("jsonView");
        MultipartFile uploadedFile = request.getFile("upload");
        String path = uploadPath.replace("/", "\\");
        String originFileName = uploadedFile.getOriginalFilename();

        // split은 String[]의 하위클래스 이기 때문에 불필요한 60개의 배열을 생성함
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

//    public void uploadImage(MultipartHttpServletRequest request) throws Exception{
//        // 파일이 저장 될 path 지정
//        String path = contextPath.replace("**","");
//        MultipartFile uploadedFile = request.getFile("upload");
//
//        // 랜덤 uuid를 생성해 원래 파일명에 추가해서 반환
//        UUID uuid = UUID.randomUUID();
//        String originFileName =  uploadedFile.getOriginalFilename();
//
//        String fileName = originFileName.substring(0, originFileName.indexOf("."));
//        String ext = originFileName.substring(originFileName.indexOf("."));
//
//        String newFileName = fileName + "_" + uuid + fileName;
//
//        File savedFile = new File(path, newFileName);
//        uploadedFile.transferTo(savedFile);
//
//
//    }
}
