package cn.shuhan.plan.controller;

import cn.shuhan.plan.domain.command.LoginInfoQueryCommand;
import cn.shuhan.plan.domain.command.UserCommand;
import cn.shuhan.plan.domain.command.UserQueryCommand;
import cn.shuhan.plan.domain.dto.ApiResp;
import cn.shuhan.plan.enums.ResultEnum;
import cn.shuhan.plan.exception.BaseException;
import cn.shuhan.plan.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kuangsha
 * @date 2020/1/7 15:21
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @ApiOperation("未登录")
    @GetMapping("/logonErr")
    public ApiResp logonErr(){
        return ApiResp.fail(ResultEnum.NO_LOGIN);
    }

    @PostMapping("/list")
    public ApiResp list(@RequestBody UserQueryCommand command){
        return ApiResp.success(userService.listUser(command));
    }

    @GetMapping("/detail/{id}")
    public ApiResp detail(@PathVariable("id") Long id){
        return ApiResp.success(userService.detail(id));
    }

    @ApiOperation("用户登录记录")
    @GetMapping("/loginInfo/{id}")
    public ApiResp loginInfo(@PathVariable("id") Long id){
        return ApiResp.success(userService.loginInfo(id));
    }

    @ApiOperation("用户信息修改")
    @PostMapping("/update")
    public ApiResp update(@RequestBody UserCommand command){
        userService.update(command);
        return ApiResp.success();
    }

    @ApiOperation("用户信息修改")
    @PostMapping("/loginInfoList")
    public ApiResp loginInfoList(@RequestBody LoginInfoQueryCommand command){
        return ApiResp.success(userService.loginInfoList(command));
    }
}
