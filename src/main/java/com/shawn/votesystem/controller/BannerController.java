//package com.shawn.votesystem.controller;
//
//
//import com.shawn.votesystem.dto.BannerDTO;
//import com.shawn.votesystem.dto.ResultMsg;
//import com.shawn.votesystem.service.BannerService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//
//@RestController
//@RequestMapping("/banner")
//@CrossOrigin(origins = "*")
//@Api(tags = "Banner")
//public class BannerController {
//
//    @Autowired
//    private BannerService bannerService;
//
//    @GetMapping(value = "/list")
//    @ApiOperation(value="获取广告列表", notes="获取广告列表")
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "Get List Successfully."),
//            @ApiResponse(code = 400, message = "Invalid input format."),
//            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//            @ApiResponse(code = 404, message = "Bad request") })
//    public ResultMsg list() {
//        return ResultMsg.successMsg("Success",bannerService.list());
//    }
//
//    @PostMapping(value = "/add")
//    @ApiOperation(value="添加广告", notes="添加广告")
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "Add Banner Successfully."),
//            @ApiResponse(code = 400, message = "Invalid input format."),
//            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//            @ApiResponse(code = 404, message = "Bad request") })
//    public ResultMsg save(BannerDTO bannerDTO, @RequestParam(value = "imageFile" ,required =false) MultipartFile imageFile) {
//        return bannerService.saveBanner(bannerDTO, imageFile);
//    }
//
//    @PutMapping(value = "/update")
//    @ApiOperation(value="修改广告信息", notes="修改广告信息")
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "Update Banner Successfully."),
//            @ApiResponse(code = 400, message = "Invalid input format."),
//            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//            @ApiResponse(code = 404, message = "Bad request") })
//    public ResultMsg update(BannerDTO bannerDTO, @RequestParam(value = "imageFile" ,required =false) MultipartFile imageFile) {
//        return bannerService.updateBanner(bannerDTO, imageFile);
//    }
//
//
//
//    @PutMapping(value = "/update/status")
//    @ApiOperation(value="修改广告状态", notes="修改广告状态")
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "Update Status Successfully."),
//            @ApiResponse(code = 400, message = "Invalid input format."),
//            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//            @ApiResponse(code = 404, message = "Bad request") })
//    public ResultMsg updateStatus(@RequestBody BannerDTO bannerDTO) {
//        return bannerService.updateStatus(bannerDTO);
//    }
//
//    @PutMapping(value = "/update/expired")
//    @ApiOperation(value="修改广告过期时间", notes="修改广告过期时间")
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "Update Expired Successfully."),
//            @ApiResponse(code = 400, message = "Invalid input format."),
//            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//            @ApiResponse(code = 404, message = "Bad request") })
//    public ResultMsg updateExpired(@RequestBody BannerDTO bannerDTO) {
//        return bannerService.updateExpiredTime(bannerDTO);
//    }
//
//    @GetMapping(value = "/url/{url}")
//    public String getFullUrl(@PathVariable String url) {
//        return bannerService.getFullUrl(url);
//    }
//}
