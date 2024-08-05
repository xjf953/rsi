package com.shawn.votesystem.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class BannerDTO implements Serializable {

    /** id*/
    private String id;

    /** banner url*/
    private String url;

    /** 超时时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiredTime;

    /** 外部链接*/
    private String extLink;

    /** 预留广告类型*/
    private int type;

    /** 0激活 1关闭*/
    private int status = 0;

    /** 描述*/
    private String describe;
}
