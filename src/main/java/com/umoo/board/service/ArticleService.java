package com.umoo.board.service;

import com.umoo.board.entity.Article;
import com.umoo.board.repository.ArticleRepository;
import com.umoo.board.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private FileRepository fileRepository;

    // multipartFile을 사용해서 파일 받기
    public void write(Article article, MultipartFile file) throws Exception{

        // 파일이 있을 때만 작업하기
        if (!file.getOriginalFilename().isEmpty()){
            // 파일이 저장 될 path 지정
            String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            // 랜덤 uuid를 생성해 원래 파일명에 추가해서 반환
            UUID uuid = UUID.randomUUID();
            String fileName =  uuid + "_" + file.getOriginalFilename();
            File savedFile = new File(path, fileName);
            file.transferTo(savedFile);

            // article에 filename 저장
//            article.setFileName(fileName);

            // file에 정보 저장
            com.umoo.board.entity.File fileEntity = new com.umoo.board.entity.File();
            fileEntity.setFileName(fileName);
            fileEntity.setFilePath(path);

            fileRepository.save(fileEntity);
        }

        articleRepository.save(article);

    }

    public List<Article> list(){

        return articleRepository.findAll();
    }

    public Article view(Long id){

        return articleRepository.findById(id).get();
    }

    public void delete(Long id){
        articleRepository.deleteById(id);
    }

}
