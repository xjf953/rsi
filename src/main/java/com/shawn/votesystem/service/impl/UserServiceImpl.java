//package com.shawn.votesystem.service.impl;
//
//import com.shawn.votesystem.constant.BaseConstant;
//import com.shawn.votesystem.dto.ResultMsg;
//import com.shawn.votesystem.dto.UserDTO;
//import com.shawn.votesystem.model.User;
//import com.shawn.votesystem.repository.UserRepository;
//import com.shawn.votesystem.service.UserService;
//import com.shawn.votesystem.utils.ModelUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDTO save(UserDTO userDTO) {
//        User banner = Optional.ofNullable(userDTO)
//                .map(UserDTO::getId).filter(StringUtils::isNotBlank)
//                .flatMap(userRepository::findById).orElse(new User());
//        ModelUtil.copyPropertiesIgnoreNull(userDTO, banner);
//        userRepository.save(banner);
//        userDTO.setId(banner.getId());
//        return userDTO;
//    }
//
//    @Override
//    public boolean checkUserName(String name) {
//        User byUserName = userRepository.findByUserName(name);
//        return byUserName != null;
//    }
//
//    @Override
//    public ResultMsg Login(UserDTO userDTO){
//        User user = userRepository.findByUserName(userDTO.getUserName());
//        if(Objects.isNull(user)){
//            return ResultMsg.failMsg(BaseConstant.VOTE_ERROR_CODE_USERNAME,"用户名不存在");
//        }
//
//        if(!user.getPassword().equals(userDTO.getPassword())){
//            return ResultMsg.failMsg(BaseConstant.VOTE_ERROR_CODE_USERNAME,"密码错误");
//        }
//
//        return null;
//    }
//
//}
