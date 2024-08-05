//package com.shawn.votesystem.init;
//
//import com.shawn.votesystem.dto.UserDTO;
//import com.shawn.votesystem.model.User;
//import com.shawn.votesystem.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class VoteInit implements CommandLineRunner {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        initUser();
//    }
//
//    private void initUser(){
//        UserDTO user = new UserDTO();
//        user.setUserName("admin")
//                .setPassword("admin123.")
//                .setName("admin");
//        if(!userService.checkUserName("admin")){
//            userService.save(user);
//        }
//    }
//}
