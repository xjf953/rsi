//package com.shawn.votesystem.processor;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.shawn.votesystem.cache.RedisCache;
//import com.shawn.votesystem.constant.BaseConstant;
//import com.shawn.votesystem.dto.ProjectDTO;
//import com.shawn.votesystem.dto.ResultMsg;
//import com.shawn.votesystem.service.ProjectService;
//import com.shawn.votesystem.service.TelethonService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Slf4j
//public class TelethonProcessor implements CommandLineRunner {
//
//    @Autowired
//    private TelethonService telethonService;
//
//    @Autowired
//    private ProjectService projectService;
//
//    @Autowired
//    private RedisCache redisCache;
//
//    @Override
//    public void run(String... args) throws Exception {
//        new Telethon("tg").start();
//    }
//
//    class Telethon extends Thread {
//        public Telethon(String name) {
//            super(name);
//        }
//        @Override
//        public void run(){
//            try {
//                //启动先休眠10s，降低cpu占用，供其它任务执行
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                log.error("TG线程启动失败", e);
//            }
//            log.info("TG线程启动");
//            while(true){
//                try {
//                    List<ProjectDTO> all = projectService.getAll();
//                    if(all.size() > 0){
//                        all.forEach(project ->{
//                            String tgGroupName = project.getTelegramLink();
//                            if(!StringUtils.isEmpty(tgGroupName)){
//                                int index = tgGroupName.indexOf("t.me/");
//                                tgGroupName = tgGroupName.substring(index);
//                                ResultMsg members = telethonService.getMembers(tgGroupName);
//                                ResultMsg pinMessage = telethonService.getPinMessage(tgGroupName);
//                                if(members.getCode() == 0){
//                                    redisCache.setCacheObject(BaseConstant.REDIS_MEMBERS_KEY+project.getId(),members.getData());
//                                }
//                                if(pinMessage.getCode() == 0){
//                                    try {
//                                        List pins = (List)pinMessage.getData();
//                                        redisCache.setCacheObject(BaseConstant.REDIS_PIN_KEY+project.getId(),pins);
//                                    }catch (Exception e){
//                                        log.error("{}置顶信息获取失败"+project.getName());
//                                    }
//
//                                }
//                            }
//                        });
//                    }
//
//                    //执行成功休息10分钟
//                    Thread.sleep(600000);
//                }catch (Exception e){
//                    try {
//                        log.error("任务执行失败:"+e.toString());
//
//                        Thread.sleep(600000);
//                    } catch (Exception ex) {
//                        log.error("TG任务执行失败");
//                    }
//
//                }
//            }
//
//
//        }
//
//    }
//}
