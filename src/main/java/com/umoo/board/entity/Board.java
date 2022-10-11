package com.umoo.board.entity;

import javax.persistence.Entity;

@Entity
public class Board {
    private Long id;
    private String title;
    private String content;
}
