package cn.shuhan.plan.controller;

import cn.shuhan.plan.domain.command.ArticleCommand;
import cn.shuhan.plan.domain.command.ArticleQueryCommand;
import cn.shuhan.plan.domain.command.LoginInfoQueryCommand;
import cn.shuhan.plan.domain.dto.ApiResp;
import cn.shuhan.plan.domain.dto.UserLoginDTO;
import cn.shuhan.plan.domain.entity.User;
import cn.shuhan.plan.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kuangsha
 * @date 2020/1/7 15:21
 */
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController {

    private final ArticleService articleService;


    private String username;

    @PostMapping("/list")
    public ApiResp list(@RequestBody ArticleQueryCommand command, @SessionAttribute("currentUser") UserLoginDTO currentUser) {
        command.setUserId(currentUser.getId());
        return ApiResp.success(articleService.listArticle(command));
    }

    @GetMapping("/detail/{id}")
    public ApiResp detail(@PathVariable("id") Long id) {
        return ApiResp.success(articleService.detail(id));
    }

    @ApiOperation("添加文章")
    @PostMapping("/create")
    public ApiResp create(@RequestBody ArticleCommand command, @SessionAttribute("currentUser") UserLoginDTO currentUser) {
        command.setUserId(currentUser.getId());
        articleService.create(command);
        return ApiResp.success();
    }

//    @ApiOperation("用户信息修改")
//    @PostMapping("/loginInfoList")
//    public ApiResp loginInfoList(@RequestBody LoginInfoQueryCommand command){
//        return ApiResp.success(articleService.loginInfoList(command));
//    }
}
