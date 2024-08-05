//package com.shawn.votesystem.service.impl;
//
//import com.shawn.votesystem.cache.RedisCache;
//import com.shawn.votesystem.constant.BaseConstant;
//import com.shawn.votesystem.dto.ResultMsg;
//import com.shawn.votesystem.service.TelethonService;
//import com.shawn.votesystem.utils.WebClientUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@Service
//@Slf4j
//public class TelethonServiceImpl implements TelethonService {
//
//    @Value("${telethonUrl}")
//    private String telethonUrl;
//
//    @Autowired
//    private WebClientUtil webClient;
//
//    @Autowired
//    private RedisCache redisCache;
//
//    @Override
//    public ResultMsg getPinMessage(String tgName){
//        String url = telethonUrl + String.format(BaseConstant.TELETHON_PIN, tgName);
//        try {
//            return webClient.get(url).getBody();
//        }catch (Exception e){
//            log.error("获取置顶消息失败:"+e.getMessage());
//        }
//        return ResultMsg.failMsg();
//    }
//
//    @Override
//    public ResultMsg getMembers(String tgName){
//        String url = telethonUrl + String.format(BaseConstant.TELETHON_MEMBERS, tgName);
//        try {
//            return webClient.get(url).getBody();
//        }catch (Exception e){
//            log.error("获取电报人数失败:"+e.getMessage());
//        }
//        return ResultMsg.failMsg();
//    }
//
//    @Override
//    public ResultMsg getPinFromRedis(String id){
//        if(StringUtils.isEmpty(id)){
//            return ResultMsg.failMsg(BaseConstant.VOTE_ERROR_CODE_PROJECT_ID,"项目ID不能为空");
//        }
//
//        List<Object> cacheList = redisCache.getCacheObject(BaseConstant.REDIS_PIN_KEY + id);
//        if(Objects.isNull(cacheList)){
//            return ResultMsg.successMsg("Success",new ArrayList<>());
//        }
//        return ResultMsg.successMsg("Success",cacheList);
//    }
//}
