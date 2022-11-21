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

import javax.persistence.EntityManager;
import java.io.File;
import java.util.UUID;
import com.umoo.board.logic.Common;

@Service
public class ArticleService {

    // 이미지 경로
    @Value("${custom.path.upload-images}")
    private String contextImgPath;
    @Value("${custom.path.images}")
    private String realImgPath;

    // 파일 경로
    @Value("${custom.path.upload-files}")
    private String contextFilePath;
    @Value("${custom.path.files}")
    private String realFilePath;

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private EntityManager entityManager;

    // multipartFile을 사용해서 파일 받기
    public void write(Article article, MultipartFile file) throws Exception {

        Article savedArticle = articleRepository.save(article);

        // 파일이 있을 때만 작업하기
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            // 파일이 저장 될 path 지정
            String path = realFilePath;

            // 랜덤 uuid를 생성해 원래 파일명에 추가해서 반환
            UUID uuid = UUID.randomUUID();
            String originFileName = file.getOriginalFilename();

            String fileName = originFileName.substring(0, originFileName.indexOf("."));
            String ext = originFileName.substring(originFileName.indexOf("."));

            String newFileName = fileName + "_" + uuid + ext;

            File savedFile = new File(path, newFileName);
            file.transferTo(savedFile);

            // file 엔티티에 정보 저장 후 연관 관계를 통해 데이터 Join
            com.umoo.board.entity.File fileEntity = new com.umoo.board.entity.File();
            fileEntity.setFileName(fileName);
            fileEntity.setFilePath(path + fileName);
            fileEntity.setArticle(savedArticle);

            fileRepository.save(fileEntity);
        }
    }

    public Page<Article> list(Boolean isDel, Pageable pageable) {

        return articleRepository.findAllByIsDel(false, pageable);
    }

    public Article view(Long id) {
        return articleRepository.findById(id).get();
    }

    public void delete(Long id) {
        Article article = view(id);
        article.setIsDel(true);
        articleRepository.save(article);

        // 실제 삭제
        // articleRepository.deleteById(id);
    }


    public Page<Article> articleSearchList(String searchKeyword, Pageable pageable) {
        return articleRepository.findByTitleContaining(searchKeyword, pageable);
    }
}

