package com.umoo.board.controller;

import com.umoo.board.entity.File;
import com.umoo.board.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    /**
     * 이미지 업데이트 Post
     */
    @PostMapping(value = "/image/upload")
    public ModelAndView uploadImage(MultipartHttpServletRequest request) throws Exception {
        return fileService.uploadImage(request);
    }
}
