package cn.shuhan.plan.controller;

import cn.shuhan.plan.domain.command.TagCommand;
import cn.shuhan.plan.domain.command.TagQueryCommand;
import cn.shuhan.plan.domain.dto.ApiResp;
import cn.shuhan.plan.domain.dto.UserLoginDTO;
import cn.shuhan.plan.service.TagService;
import cn.shuhan.plan.service.TagService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kuangsha
 * @date 2020/1/7 15:21
 */
@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagController {

    private final TagService tagService;


    private String username;

    @PostMapping("/list")
    public ApiResp list(@RequestBody TagQueryCommand command, @SessionAttribute("currentUser") UserLoginDTO currentUser) {
        command.setUserId(currentUser.getId());
        return ApiResp.success(tagService.listTag(command));
    }

    @GetMapping("/detail/{id}")
    public ApiResp detail(@PathVariable("id") Long id) {
        return ApiResp.success(tagService.detail(id));
    }

    @ApiOperation("添加文章")
    @PostMapping("/create")
    public ApiResp create(@RequestBody TagCommand command, @SessionAttribute("currentUser") UserLoginDTO currentUser) {
        command.setUserId(currentUser.getId());
        tagService.create(command);
        return ApiResp.success();
    }

    @ApiOperation("修改标签")
    @PostMapping("/update")
    public ApiResp update(@RequestBody TagCommand command, @SessionAttribute("currentUser") UserLoginDTO currentUser) {
        command.setUserId(currentUser.getId());
        tagService.updateTag(command);
        return ApiResp.success();
    }

    @ApiOperation("启用禁用 status 1 启用 0禁用 、删除 dataStatus 1 正常 0删除")
    @GetMapping("/changeStatus")
    public ApiResp changeStatus(@RequestParam("id") Long id,
                                @RequestParam("status") Integer status,
                                @RequestParam("dataStatus") Integer dataStatus) {
        tagService.changeStatus(id, status, dataStatus);
        return ApiResp.success();
    }

}
