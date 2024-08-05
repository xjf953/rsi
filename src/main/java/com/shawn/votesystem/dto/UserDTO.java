package com.shawn.votesystem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class UserDTO {

    /** id*/
    private String id;

    /** 用户名*/
    private String userName;

    /** 密码*/
    private String password;

    /** 姓名*/
    private String name;
}
