//package com.shawn.votesystem.repository;
//
//import com.shawn.votesystem.model.Banner;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.List;
//
//
//@Repository
//public interface BannerRepository extends JpaRepository<Banner, String> {
//
//    List<Banner> findAllByExpiredTimeAfterAndStatus(Date now, int status);
//
//
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "update Banner pr set pr.status=?1 where pr.id=?2")
//    int updateStatus(int status, String id);
//
//}
