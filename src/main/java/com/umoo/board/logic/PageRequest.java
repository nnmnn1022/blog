package com.umoo.board.logic;

import org.springframework.data.domain.Sort;

public final class PageRequest {
    private int page = 1 ;
    private int size = 10;
    private Sort.Direction direction = Sort.Direction.DESC;

    public void setPage(int page){
        // page가 0이면 1 처리
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size){
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        // 설정한 페이지 사이즈가 50보다 크면 50으로 처리
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    // getter
    public void setDirection(Sort.Direction direction){
        this.direction = direction;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page -1, size, direction, "id");
    }
}
