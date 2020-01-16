package cn.shuhan.plan.controller;


import cn.shuhan.plan.domain.command.ReplyCommand;
import cn.shuhan.plan.domain.dto.ApiResp;
import cn.shuhan.plan.service.ReplyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author kuangsha
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class ReplyController {

    private final ReplyService replyService;


    @ApiOperation("获取评论")
    @GetMapping("/getReply")
    public ApiResp getReply(@RequestParam("articleId") Long articleId) {
        return ApiResp.success(replyService.getReply(articleId));
    }

    @ApiOperation(value = "新建", httpMethod = "POST")
    @ApiImplicitParam(dataTypeClass = ReplyCommand.class)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ApiResp create(@RequestBody ReplyCommand command) {
        replyService.create(command);
        return ApiResp.success();
    }

//    @ApiOperation(value = "修改", httpMethod = "POST")
//    @ApiImplicitParam(dataTypeClass = replyCommand.class)
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public ApiResp update(@RequestBody ChargerSheetCommand command) {
//        replyService.update(command);
//        return ApiResp.success();
//    }
//
    @ApiOperation(value = "删除", httpMethod = "GET")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ApiResp delete(@RequestParam("id") Long id) {
        replyService.delete(id);
        return ApiResp.success();
    }

}


