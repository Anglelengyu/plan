package cn.shuhan.plan.controller;

import cn.shuhan.plan.domain.dto.ApiResp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @RequestMapping("/register")
    public ApiResp thomeTest(){
        return ApiResp.success();
    }
}
