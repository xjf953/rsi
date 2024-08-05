//package com.shawn.votesystem.model;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Getter
//@Setter
//@Table(name = "PROJECT")
//@Entity
//@Accessors(chain = true)
//public class Project extends BaseModel{
//
//    /** 代币名称*/
//    @Column(name = "NAME", length = 50)
//    private String name;
//
//    /** 代币符号*/
//    @Column(name = "SYMBOL", length = 50)
//    private String symbol;
//
//    /** 项目描述*/
//    @Column(name = "DESCRIPTION", length = 500)
//    private String description;
//
//    /** 代币所属链*/
//    @Column(name = "CHAIN", length = 50)
//    private String chain;
//
//    /** 合约地址*/
//    @Column(name = "CONTRACT_ADDRESS", length = 50)
//    private String contractAddress;
//
//    /** 白名单任务链接*/
//    @Column(name = "WHITELIST_LINK")
//    private String whitelistLink;
//
//    /** 预售时间*/
//    @Column(name = "PRESALE_TIME")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date presaleTime;
//
//    /** 预售平台*/
//    @Column(name = "PRESALE_PLATFORM", length = 50)
//    private String presalePlatform;
//
//    /** 预售链接*/
//    @Column(name = "PRESALE_LINK")
//    private String presaleLink;
//
//    /** 发射时间*/
//    @Column(name = "LAUNCH_TIME")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date launchTime;
//
//    /** 发射平台*/
//    @Column(name = "LAUNCH_PLATFORM", length = 50)
//    private String launchPlatform;
//
//    /** 官网*/
//    @Column(name = "WEBSITE_LINK")
//    private String websiteLink;
//
//    /** tg群组链接*/
//    @Column(name = "TELEGRAM_LINK", length = 50)
//    private String telegramLink;
//
//    /** tg中文群组链接*/
//    @Column(name = "TELEGRAM_CHINESE", length = 50)
//    private String telegramChinese;
//
//    /** btok链接*/
//    @Column(name = "BTOK_LINK", length = 50)
//    private String btokLink;
//
//    /** 推特链接*/
//    @Column(name = "TWITTER_LINK")
//    private String twitterLink;
//
//    /** discord链接*/
//    @Column(name = "DISCORD")
//    private String discord;
//
//    @Column(name = "LOGO")
//    private String logo;
//
//    /** 联系邮箱*/
//    @Column(name = "CONTACT_EMAIL", length = 50)
//    private String contactEmail;
//
//    /** 联系TG*/
//    @Column(name = "CONTACT_TELEGRAM", length = 50)
//    private String contactTelegram;
//
//    /** 项目状态 0：激活 1：审核 2：关闭*/
//    @Column(name = "STATUS", length = 5)
//    private int status;
//
//    /** 票数*/
//    @Column(name = "COUNT", length = 10)
//    private int count;
//
//}
