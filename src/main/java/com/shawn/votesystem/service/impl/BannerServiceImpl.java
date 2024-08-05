package com.shawn.votesystem.service.impl;

import com.shawn.votesystem.constant.BaseConstant;
import com.shawn.votesystem.dto.BannerDTO;
import com.shawn.votesystem.dto.ResultMsg;
import com.shawn.votesystem.model.Banner;
import com.shawn.votesystem.repository.BannerRepository;
import com.shawn.votesystem.service.BannerService;
import com.shawn.votesystem.utils.ModelUtil;
import com.shawn.votesystem.utils.RequestUtil;
import com.shawn.votesystem.utils.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class BannerServiceImpl implements BannerService {


    @Value("${vote.profile}")
    private String baseUrl;

    @Autowired
    private BannerRepository bannerRepository;


    @Override
    public BannerDTO save(BannerDTO bannerDTO) {
        Banner banner = Optional.ofNullable(bannerDTO)
                .map(BannerDTO::getId).filter(StringUtils::isNotBlank)
                .flatMap(bannerRepository::findById).orElse(new Banner());
        ModelUtil.copyPropertiesIgnoreNull(bannerDTO, banner);
        bannerRepository.save(banner);
        bannerDTO.setId(banner.getId());
        return bannerDTO;
    }

    @Override
    public List<BannerDTO> list(){
        List<Banner> banners = bannerRepository.findAllByExpiredTimeAfterAndStatus(new Date(), BaseConstant.BANNER_STATUS_ACTIVATION);
        if(Objects.nonNull(banners)){
            return ModelUtil.copyListProperties(banners, BannerDTO.class);
        }
        return new ArrayList<>();
    }

    @Override
    public ResultMsg saveBanner(BannerDTO bannerDTO, MultipartFile file){
        if(Objects.isNull(file)){
            return ResultMsg.failMsg("图片文件不能为空");
        }
        try {
            String fileName = System.currentTimeMillis() + "";
            String savePath = UploadUtil.getRootPath() + baseUrl;
            String realFileName = UploadUtil.fileUp(file, savePath, fileName);
            bannerDTO.setUrl(realFileName);
            save(bannerDTO);
        }catch (Exception e){
            return ResultMsg.failMsg();
        }
        return ResultMsg.successMsg();
    }

    @Override
    public ResultMsg updateBanner(BannerDTO bannerDTO, MultipartFile file){

        try {
            if(Objects.nonNull(file)){
                String fileName = System.currentTimeMillis() + "";
                String savePath = UploadUtil.getRootPath() + baseUrl;
                String realFileName = UploadUtil.fileUp(file, savePath, fileName);
                bannerDTO.setUrl(realFileName);
            }
            save(bannerDTO);
        }catch (Exception e){
            return ResultMsg.failMsg();
        }
        return ResultMsg.successMsg();
    }


    @Override
    public ResultMsg updateStatus(BannerDTO bannerDTO){
        int res = bannerRepository.updateStatus(bannerDTO.getStatus(), bannerDTO.getId());
        return res > 0 ? ResultMsg.successMsg() : ResultMsg.failMsg();
    }

    @Override
    public ResultMsg updateExpiredTime(BannerDTO bannerDTO){
        save(bannerDTO);
        return ResultMsg.successMsg();
    }

    @Override
    public String getFullUrl(String url){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = RequestUtil.getURI(request);
        return uri + baseUrl + url;
    }
}
