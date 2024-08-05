package com.shawn.votesystem.controller;


import com.shawn.votesystem.dto.ProjectRequestDTO;
import com.shawn.votesystem.dto.ResultMsg;
import com.shawn.votesystem.service.ProjectService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "*")
@Api(tags = "Project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    @ApiOperation(value="分页获取项目列表", notes="项目列表")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Get List Successfully."),
            @ApiResponse(code = 400, message = "Invalid input format."),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Bad request") })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "10", paramType = "query", dataType = "int"),
    })
    public ResultMsg list(int page, int pageSize, int status) {
        return ResultMsg.successMsg("Success",projectService.list(page,pageSize,status));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value="添加项目", notes="添加项目")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Add Project Successfully."),
            @ApiResponse(code = 400, message = "Invalid input format."),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Bad request") })
    public ResultMsg add(ProjectRequestDTO requestDTO, @RequestParam(value = "imageFile" ,required =false) MultipartFile imageFile) {
        return ResultMsg.successMsg("Success",projectService.add(requestDTO, imageFile));
    }

    @PutMapping(value = "/update")
    @ApiOperation(value="修改项目信息", notes="修改项目信息")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Update Project Successfully."),
            @ApiResponse(code = 400, message = "Invalid input format."),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Bad request") })
    public ResultMsg update(ProjectRequestDTO requestDTO, @RequestParam(value = "imageFile" ,required =false) MultipartFile imageFile) {
        return ResultMsg.successMsg("Success",projectService.update(requestDTO, imageFile));
    }

    @GetMapping(value = "/hot")
    @ApiOperation(value="获取投票数前十的项目", notes="获取投票数前十的项目")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Get Hot List Successfully."),
            @ApiResponse(code = 400, message = "Invalid input format."),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Bad request") })
    public ResultMsg hotList() {
        return ResultMsg.successMsg("Success",projectService.hotList());
    }

    @PutMapping(value = "/status")
    @ApiOperation(value="修改项目状态", notes="修改项目状态")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Update Project Status Successfully."),
            @ApiResponse(code = 400, message = "Invalid input format."),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Bad request") })
    public ResultMsg update(@RequestBody ProjectRequestDTO requestDTO) {
        return projectService.updateStatus(requestDTO);
    }

}
