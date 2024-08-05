package com.shawn.votesystem.service;

import com.shawn.votesystem.dto.PageItem;
import com.shawn.votesystem.dto.ProjectDTO;
import com.shawn.votesystem.dto.ProjectRequestDTO;
import com.shawn.votesystem.dto.ResultMsg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {

    ProjectRequestDTO save(ProjectRequestDTO projectDTO);

    ProjectRequestDTO add(ProjectRequestDTO projectDTO, MultipartFile imageFile);

    ProjectRequestDTO update(ProjectRequestDTO projectDTO, MultipartFile imageFile);

    PageItem list(int page, int pageSize, int status);

    ProjectDTO getById(String id);

    void addVote(String id, int count);

    List<ProjectDTO> getAll();

    List<ProjectDTO> hotList();

    ResultMsg updateStatus(ProjectRequestDTO requestDTO);
}
