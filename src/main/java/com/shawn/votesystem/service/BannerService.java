package com.shawn.votesystem.service;

import com.shawn.votesystem.dto.BannerDTO;
import com.shawn.votesystem.dto.ResultMsg;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface BannerService {
    BannerDTO save(BannerDTO bannerDTO);

    List<BannerDTO> list();

    ResultMsg saveBanner(BannerDTO bannerDTO, MultipartFile file);

    ResultMsg updateBanner(BannerDTO bannerDTO, MultipartFile file);

    ResultMsg updateStatus(BannerDTO bannerDTO);

    ResultMsg updateExpiredTime(BannerDTO bannerDTO);

    String getFullUrl(String url);
}
