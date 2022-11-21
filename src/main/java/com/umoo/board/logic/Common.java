package com.umoo.board.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Common {
    /**
     * 오늘 올린 게시글이면 시간 반환
     * 아니면 날짜 반환
     */
    public String getDate(LocalDateTime regDate) {
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter ymd = DateTimeFormatter.ofPattern("yy-MM-dd");
        DateTimeFormatter hm = DateTimeFormatter.ofPattern("HH:mm");

        // 포맷 적용
        String formattedNow = now.format(ymd);
        String formattedRegDate = regDate.format(ymd);

        if (formattedNow == formattedRegDate){
            return regDate.format(ymd);
        }else{
            return formattedRegDate;
        }

    }
}
