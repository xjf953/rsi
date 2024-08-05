package com.shawn.votesystem.repository;

import com.shawn.votesystem.dto.ProjectDTO;
import com.shawn.votesystem.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Project pr set pr.count=?1 where pr.id=?2")
    void addVote(int count,  String id);

    List<Project> findAllByStatus(int status);

    List<Project> findTop10ByStatusOrderByCountDesc(int status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Project pr set pr.status=?1 where pr.id=?2")
    int updateStatus(int status,  String id);

}
