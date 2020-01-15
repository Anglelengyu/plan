package cn.shuhan.plan.controller;

import cn.shuhan.plan.domain.command.ArticleCommand;
import cn.shuhan.plan.domain.command.ArticleQueryCommand;
import cn.shuhan.plan.domain.dto.ApiResp;
import cn.shuhan.plan.domain.dto.UserLoginDTO;
import cn.shuhan.plan.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public ApiResp list(@RequestBody ArticleQueryCommand command) {
        return ApiResp.success(articleService.listArticle(command));
    }

    @ApiOperation("用户文章")
    @PostMapping("/listByUserId")
    public ApiResp listByUserId(@RequestBody ArticleQueryCommand command, @SessionAttribute("currentUser") UserLoginDTO currentUser) {
        command.setUserId(currentUser.getId());
        return ApiResp.success(articleService.listByUserId(command));
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

    @ApiOperation("编辑文章")
    @PostMapping("/update")
    public ApiResp update(@RequestBody ArticleCommand command, @SessionAttribute("currentUser") UserLoginDTO currentUser) {
        command.setUserId(currentUser.getId());
        articleService.updateArt(command);
        return ApiResp.success();
    }

    @ApiOperation("删除文章")
    @GetMapping("/deleteArt")
    public ApiResp deleteArt(@RequestParam("id") Long id, @SessionAttribute("currentUser") UserLoginDTO currentUser) {
        articleService.deleteArt(id);
        return ApiResp.success();
    }
}
