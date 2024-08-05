package com.shawn.votesystem.controller;


import com.shawn.votesystem.dto.ResultMsg;
import com.shawn.votesystem.dto.VoteEventDTO;
import com.shawn.votesystem.service.VoteEventService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vote")
@CrossOrigin(origins = "*")
@Api(tags = "Vote")
public class VoteEventController {

    @Autowired
    private VoteEventService voteEventService;


    @PostMapping
    @ApiOperation(value="投票", notes="投票")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Vote Successfully."),
            @ApiResponse(code = 400, message = "Invalid input format."),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Bad request") })
    public ResultMsg vote(@RequestBody VoteEventDTO eventDTO) {
        return voteEventService.save(eventDTO);
    }
}
