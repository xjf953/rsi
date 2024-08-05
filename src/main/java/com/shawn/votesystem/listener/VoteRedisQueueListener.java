package com.shawn.votesystem.listener;


import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.shawn.votesystem.config.FastJson2JsonRedisSerializer;
import com.shawn.votesystem.dto.VoteEventDTO;
import com.shawn.votesystem.service.VoteEventService;
import com.shawn.votesystem.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Order(value = 100)
public class VoteRedisQueueListener implements MessageListener{

    private VoteEventService voteEventService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        //消息处理
        voteEventService = SpringContextHolder.getBean("voteEventServiceImpl");
        RedisSerializer serializer = new FastJson2JsonRedisSerializer(VoteEventDTO.class);
        VoteEventDTO voteEventDTO = (VoteEventDTO) serializer.deserialize(message.getBody());
        voteEventService.processVote(voteEventDTO);
        log.info("IP:{},项目ID:{}",voteEventDTO.getIp(),voteEventDTO.getProjectId());
    }
}
