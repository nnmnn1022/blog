package com.umoo.board.service;

import com.umoo.board.entity.Article;
import com.umoo.board.repository.ArticleRepository;
import com.umoo.board.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Value("${custom.path.upload-path}")
    private String uploadPath;

    @Value("${custom.path.upload-images}")
    private String contextPath;

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private FileRepository fileRepository;

    // multipartFile을 사용해서 파일 받기
    public void write(Article article, MultipartFile file) throws Exception{

        Article savedArticle = articleRepository.save(article);

        // 파일이 있을 때만 작업하기
        if (file != null && !file.getOriginalFilename().isEmpty()){
            // 파일이 저장 될 path 지정
            String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            // 랜덤 uuid를 생성해 원래 파일명에 추가해서 반환
            UUID uuid = UUID.randomUUID();
            String originFileName =  file.getOriginalFilename();

            String fileName = originFileName.substring(0, originFileName.indexOf("."));
            String ext = originFileName.substring(originFileName.indexOf("."));

            String newFileName = fileName + "_" + uuid + fileName;

            File savedFile = new File(path, newFileName);
            file.transferTo(savedFile);

            // file 엔티티에 정보 저장 후 연관 관계를 통해 데이터 Join
            com.umoo.board.entity.File fileEntity = new com.umoo.board.entity.File();
            fileEntity.setFileName(fileName);
            fileEntity.setFilePath(uploadPath + fileName);
            fileEntity.setArticle(savedArticle);

            fileRepository.save(fileEntity);
        }
    }

    public Page<Article> list(Pageable pageable){

        return articleRepository.findAll(pageable);
    }

    public Article view(Long id){
        return articleRepository.findById(id).get();
    }

    public void delete(Long id){
        articleRepository.deleteById(id);
    }

    public Page<Article> articleSearchList(String searchKeyword, Pageable pageable){
        return articleRepository.findByTitleContaining(searchKeyword, pageable);
    }

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
