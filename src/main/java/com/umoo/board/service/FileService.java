package com.umoo.board.service;

import com.umoo.board.entity.Article;
import com.umoo.board.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;

@Service
public class FileService {

    @Value("${custom.path.images}")
    private String realPath;

    @Value("${custom.path.upload-images}")
    private String contextPath;

    @Autowired
    private FileRepository fileRepository;

    public ModelAndView uploadImage(MultipartHttpServletRequest request) throws Exception{

        ModelAndView mav = new ModelAndView("jsonView");
        MultipartFile uploadedFile = request.getFile("upload");
        String originFileName = uploadedFile.getOriginalFilename();

        // split은 String[]의 하위클래스 이기 때문에 불필요한 60개의 배열을 생성함
        String fileName = originFileName.substring(0, originFileName.indexOf("."));
        String ext = originFileName.substring(originFileName.indexOf("."));

        String newFileName = fileName + "_" + UUID.randomUUID() + ext;

        File file = new File(realPath, newFileName);
        uploadedFile.transferTo(file);

        mav.addObject("uploaded", true);
        mav.addObject("url", contextPath.replace("**", newFileName));

        return mav;
    }

    public List<com.umoo.board.entity.File> view(Long id){
        return fileRepository.findByArticleId(id);
    }
}
