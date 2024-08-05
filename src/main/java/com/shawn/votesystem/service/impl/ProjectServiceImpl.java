//package com.shawn.votesystem.service.impl;
//
//import com.shawn.votesystem.cache.RedisCache;
//import com.shawn.votesystem.constant.BaseConstant;
//import com.shawn.votesystem.dto.*;
//import com.shawn.votesystem.model.Project;
//import com.shawn.votesystem.repository.ProjectRepository;
//import com.shawn.votesystem.service.ProjectService;
//import com.shawn.votesystem.utils.ModelUtil;
//import com.shawn.votesystem.utils.UploadUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//
//@Service
//@Slf4j
//public class ProjectServiceImpl  implements ProjectService {
//
//    @Value("${vote.profile}")
//    private String baseUrl;
//
//    @Autowired
//    private ProjectRepository projectRepository;
//
//    @Autowired
//    private RedisCache redisCache;
//
//
//    @Override
//    public ProjectRequestDTO save(ProjectRequestDTO projectDTO) {
//        Project project = Optional.ofNullable(projectDTO)
//                .map(ProjectRequestDTO::getId).filter(StringUtils::isNotBlank)
//                .flatMap(projectRepository::findById).orElse(new Project());
//        ModelUtil.copyPropertiesIgnoreNull(projectDTO, project);
//        projectRepository.save(project);
//        projectDTO.setId(project.getId());
//        return projectDTO;
//    }
//
//    @Override
//    public ProjectRequestDTO add(ProjectRequestDTO projectDTO, MultipartFile imageFile) {
//        try {
//            String fileName = System.currentTimeMillis() + "";
//            String savePath = UploadUtil.getRootPath() + baseUrl;
//            String realFileName = UploadUtil.fileUp(imageFile, savePath, fileName);
//            projectDTO.setLogo(realFileName);
//            projectDTO.setStatus(1);
//            projectDTO = save(projectDTO);
//        }catch (Exception e){
//            log.error("添加项目失败:{}",e.getMessage());
//        }
//        return projectDTO;
//    }
//
//    @Override
//    public ProjectRequestDTO update(ProjectRequestDTO projectDTO, MultipartFile imageFile) {
//        try {
//            if (Objects.nonNull(imageFile)) {
//                String fileName = System.currentTimeMillis() + "";
//                String savePath = UploadUtil.getRootPath() + baseUrl;
//                String realFileName = UploadUtil.fileUp(imageFile, savePath, fileName);
//                projectDTO.setLogo(realFileName);
//            }
//            projectDTO = save(projectDTO);
//        }catch (Exception e){
//            log.error("修改项目失败");
//        }
//        return projectDTO;
//    }
//
//    @Override
//    public PageItem list(int page, int pageSize, int status) {
//        Project project = new Project();
//        project.setStatus(status);
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("count");
//        Example<Project> option = Example.of(project,matcher);
//        Page<Project> list = projectRepository.findAll(option,PageRequest.of(page-1,pageSize,Sort.Direction.DESC,"createTime"));
//        List<ProjectDTO> projectDTOS = ModelUtil.copyListProperties(list.getContent(), ProjectDTO.class);
//        projectDTOS.forEach(projectDTO -> {
//            Object members = redisCache.getCacheObject(BaseConstant.REDIS_MEMBERS_KEY + projectDTO.getId());
//            projectDTO.setMembers(members == null ? 0 : Integer.parseInt(members.toString()));
//        });
//        return new PageItem()
//                .setPage(page)
//                .setPageSize(pageSize)
//                .setData(projectDTOS)
//                .setTotal(list.getTotalElements())
//                .setTotalPage(list.getTotalPages());
//    }
//
//    @Override
//    public ProjectDTO getById(String id) {
//        Optional<Project> project = projectRepository.findById(id);
//        return project.map(value -> ModelUtil.copyProperties(value, new ProjectDTO())).orElseGet(ProjectDTO::new);
//    }
//
//    @Override
//    public void addVote(String id, int count) {
//        projectRepository.addVote(count,id);
//    }
//
//    @Override
//    public List<ProjectDTO> getAll() {
//        List<ProjectDTO> projectDTOS = new ArrayList<>();
//        List<Project> allByStatus = projectRepository.findAllByStatus(BaseConstant.PROJECT_STATUS_ACTIVATION);
//        if(Objects.isNull(allByStatus) || allByStatus.size() == 0){
//            return projectDTOS;
//        }
//        return ModelUtil.copyListProperties(allByStatus, ProjectDTO.class);
//    }
//
//    @Override
//    public List<ProjectDTO> hotList() {
//        List<ProjectDTO> projectDTOS = new ArrayList<>();
//        List<Project> allByStatus = projectRepository.findTop10ByStatusOrderByCountDesc(BaseConstant.PROJECT_STATUS_ACTIVATION);
//        if(Objects.isNull(allByStatus) || allByStatus.size() == 0){
//            return projectDTOS;
//        }
//        return ModelUtil.copyListProperties(allByStatus, ProjectDTO.class);
//    }
//
//    @Override
//    public ResultMsg updateStatus(ProjectRequestDTO requestDTO){
//        int res = projectRepository.updateStatus(requestDTO.getStatus(), requestDTO.getId());
//        return res > 0 ? ResultMsg.successMsg() : ResultMsg.failMsg();
//    }
//
//
//}
