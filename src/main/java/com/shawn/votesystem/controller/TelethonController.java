package com.shawn.votesystem.controller;


import com.shawn.votesystem.dto.ResultMsg;
import com.shawn.votesystem.service.TelethonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telethon")
@CrossOrigin(origins = "*")
@Api(tags = "Telethon")
public class TelethonController {

    @Autowired
    private TelethonService telethonService;

    @GetMapping(value = "/pin/{id}")
    @ApiOperation(value="获取置顶信息", notes="获取最近20条置顶消息")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Get Pin Message Successfully."),
            @ApiResponse(code = 400, message = "Invalid input format."),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Bad request") })
    public ResultMsg pin(@PathVariable String id) {
        return telethonService.getPinFromRedis(id);
    }
}
