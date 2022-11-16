package com.umoo.board.Service;

import com.umoo.board.entity.File;
import com.umoo.board.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
class FileServiceTest {

    @Autowired
    private FileRepository fileRepository;

    @Test
    void view() {
        //given
        Long id = 20L;

        //when
        List<File> file_list = fileRepository.findByArticleId(id);
        File file = file_list.get(-1);

        //then
        assertThat(file).isEqualTo(fileRepository.findAll().get(-1));

    }
}