package com.shawn.votesystem.config;

import com.shawn.votesystem.constant.BaseConstant;
import com.shawn.votesystem.listener.VoteRedisQueueListener;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SuppressWarnings({"all"})
@Configuration
@EnableCaching
public class RedisQueueConfig {

    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    //相当于xml中的bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter HospitalmanageAdapter, MessageListenerAdapter DepartmentAdapter
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        //订阅通道
        container.addMessageListener(HospitalmanageAdapter, new PatternTopic(BaseConstant.REDIS_TOPIC));
        //这个container 可以添加多个 messageListener
        return container;
    }

    @Bean
    MessageListenerAdapter VoteRedisQueueAdapter() {
        return new MessageListenerAdapter(new VoteRedisQueueListener());
    }
}
