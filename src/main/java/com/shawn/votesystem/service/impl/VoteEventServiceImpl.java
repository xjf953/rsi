//package com.shawn.votesystem.service.impl;
//
//import com.shawn.votesystem.constant.BaseConstant;
//import com.shawn.votesystem.dto.ProjectDTO;
//import com.shawn.votesystem.dto.ResultMsg;
//import com.shawn.votesystem.dto.VoteEventDTO;
//import com.shawn.votesystem.model.VoteEvent;
//import com.shawn.votesystem.repository.VoteEventRepository;
//import com.shawn.votesystem.service.ProjectService;
//import com.shawn.votesystem.service.TelethonService;
//import com.shawn.votesystem.service.VoteEventService;
//import com.shawn.votesystem.utils.DateUtil;
//import com.shawn.votesystem.utils.ModelUtil;
//import com.shawn.votesystem.utils.RequestUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//import java.util.Objects;
//
//@Service
//@Slf4j
//public class VoteEventServiceImpl implements VoteEventService {
//
//    @Autowired
//    private VoteEventRepository voteEventRepository;
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Autowired
//    private ProjectService projectService;
//
//    @Autowired
//    private TelethonService telethonService;
//
//
//
//    @Override
//    public ResultMsg save(VoteEventDTO eventDTO) {
//        if(StringUtils.isEmpty(eventDTO.getProjectId())){
//            return ResultMsg.failMsg("项目ID不能为空");
//        }
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String ip = RequestUtil.getIp(request);
//        eventDTO.setIp(ip);
//        VoteEvent byProjectIdAndIp = voteEventRepository.findFirstByProjectIdAndIpOrderByCreateTimeDesc(eventDTO.getProjectId(), eventDTO.getIp());
//        if(Objects.nonNull(byProjectIdAndIp)){
//            Date createTime = byProjectIdAndIp.getCreateTime();
//            Date time = DateUtil.addMinute(createTime,10);
//            //投票间隔十分钟
//            if(time.before(new Date())){
//                redisTemplate.convertAndSend(BaseConstant.REDIS_TOPIC, eventDTO);
//                return ResultMsg.successMsg("投票成功");
//            }
//            return ResultMsg.failMsg("投票间隔未超过十分钟");
//        }
//        redisTemplate.convertAndSend(BaseConstant.REDIS_TOPIC, eventDTO);
//        return ResultMsg.successMsg("投票成功");
//    }
//
//    @Override
//    public void processVote(VoteEventDTO eventDTO){
//        synchronized (this){
//            VoteEvent byProjectIdAndIp = voteEventRepository.findFirstByProjectIdAndIpOrderByCreateTimeDesc(eventDTO.getProjectId(), eventDTO.getIp());
//            VoteEvent voteEvent = ModelUtil.copyProperties(eventDTO,new VoteEvent());
//            if(Objects.nonNull(byProjectIdAndIp)){
//                Date createTime = byProjectIdAndIp.getCreateTime();
//                Date time = DateUtil.addMinute(createTime,10);
//                //投票间隔十分钟
//                if(time.before(new Date())){
//                    voteEventRepository.save(voteEvent);
//                    ProjectDTO projectDTO = projectService.getById(voteEvent.getProjectId());
//                    projectService.addVote(projectDTO.getId(),projectDTO.getCount() + 1);
//                    log.info("{}投票成功,当前票数:{}",projectDTO.getName(),projectDTO.getCount()+1);
//                }
//            }else{
//                //第一次投票
//                voteEvent = ModelUtil.copyProperties(eventDTO,new VoteEvent());
//                voteEventRepository.save(voteEvent);
//                ProjectDTO projectDTO = projectService.getById(voteEvent.getProjectId());
//                projectService.addVote(projectDTO.getId(),projectDTO.getCount() + 1);
//                log.info("{}投票成功,当前票数:{}",projectDTO.getName(),projectDTO.getCount()+1);
//            }
//
//        }
//    }
//}
