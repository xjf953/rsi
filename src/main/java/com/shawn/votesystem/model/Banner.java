package com.shawn.votesystem.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Table(name = "Banner")
@Entity
@Accessors(chain = true)
public class Banner extends BaseModel{

    /** banner url*/
    @Column(name = "URL", length = 50)
    private String url;

    /** 超时时间*/
    @Column(name = "EXPIRED_TIME")
    private Date expiredTime;

    /** 外部链接*/
    @Column(name = "EXT_LINK", length = 500)
    private String extLink;

    /** 预留*/
    @Column(name = "TYPE", length = 2)
    private int type;

    /** 0激活 1关闭*/
    @Column(name = "STATUS", length = 2)
    private int status;

    /** 描述*/
    @Column(name = "DESCRIBE", length = 500)
    private String describe;
}
