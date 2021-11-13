package com.wk.cloud.web;

import com.wk.cloud.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/7 5:53 下午
 * @description：
 */
@RestController
@RequestMapping("/sys")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @GetMapping("/user")
    public Object getUser(){
        return "aaaaaa";
    }
}
