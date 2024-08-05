package com.shawn.votesystem.model;


import com.shawn.votesystem.model.convert.EncryptConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "VOTE_USER")
@Entity
@Accessors(chain = true)
public class User extends BaseModel{

    /** 用户名*/
    @Column(name = "USER_NAME", length = 50)
    private String userName;

    /** 密码*/
    @Column(name = "PASSWORD", length = 50)
    @Convert(converter = EncryptConverter.class)
    private String password;

    /** 姓名*/
    @Column(name = "NAME", length = 50)
    private String name;
}
