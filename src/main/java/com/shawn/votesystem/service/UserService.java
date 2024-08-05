package com.shawn.votesystem.service;

import com.shawn.votesystem.dto.ResultMsg;
import com.shawn.votesystem.dto.UserDTO;

public interface UserService {

    UserDTO save(UserDTO userDTO);

    boolean checkUserName(String name);

    ResultMsg Login(UserDTO userDTO);
}
