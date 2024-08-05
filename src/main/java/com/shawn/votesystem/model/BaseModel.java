//package com.shawn.votesystem.model;
//
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
//@Getter
//@Setter
//@MappedSuperclass
//public class BaseModel implements Serializable {
//
//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//    @Column(name = "ID", length = 50, nullable = false)
//    private String id;
//
//    @Column(name = "CREATE_TIME", updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
//    private Date createTime;
//
//
//    @Column(name = "UPDATE_TIME")
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
//    private Date updateTime;
//}
