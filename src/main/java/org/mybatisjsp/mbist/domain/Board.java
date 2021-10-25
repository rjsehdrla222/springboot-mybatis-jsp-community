package org.mybatisjsp.mbist.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Board {

    private Long id;
    private String name;
    private String title;
    private String content;
    private String pw;
    private int viewcnt;
    private Timestamp create_date;
}
