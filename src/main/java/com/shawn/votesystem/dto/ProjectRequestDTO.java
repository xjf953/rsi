package com.shawn.votesystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProjectRequestDTO implements Serializable {

    private String id;

    /** 代币名称*/
    private String name;

    /** 代币符号*/
    private String symbol;

    /** 项目描述*/
    private String description;

    /** 代币所属链*/
    private String chain;

    /** 合约地址*/
    private String contractAddress;

    /** 白名单任务链接*/
    private String whitelistLink;

    /** 预售时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date presaleTime;

    /** 预售平台*/
    private String presalePlatform;

    /** 预售链接*/
    private String presaleLink;

    /** 发射时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date launchTime;

    /** 发射平台*/
    private String launchPlatform;

    /** 官网*/
    private String websiteLink;

    /** tg群组链接*/
    private String telegramLink;

    /** tg中文群组链接*/
    private String telegramChinese;

    /** btok链接*/
    private String btokLink;

    /** 推特链接*/
    private String twitterLink;

    /** discord链接*/
    private String discord;

    /** 项目Logo*/
    private String logo;

    /** 联系邮箱*/
    private String contactEmail;

    /** 联系TG*/
    private String contactTelegram;

    /** 项目状态 0：激活 1：审核 2：关闭*/
    private int status;

    /** 更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
