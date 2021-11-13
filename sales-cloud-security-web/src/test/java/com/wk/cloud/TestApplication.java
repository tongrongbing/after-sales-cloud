package com.wk.cloud;

import com.alibaba.fastjson.JSON;
import com.wk.cloud.modules.SysRole;
import com.wk.cloud.modules.SysUser;
import com.wk.cloud.service.SysRoleService;
import com.wk.cloud.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/13 11:59 上午
 * @description： 测试启动类（com.wk.cloud）
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestApplication {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;
    @Test
    public void testGetSysUser(){
        List<SysUser> list = sysUserService.list();
        log.info(JSON.toJSONString(list));
    }

    @Test
    public void testFindUserByName(){
        SysUser admin = sysUserService.findUserByName("admin");
        log.info(JSON.toJSONString(admin));
    }

    @Test
    public void testGetSysRoles(){
        List<SysRole> sysRoles = sysRoleService.list();
        log.info(JSON.toJSONString(sysRoles));
    }
}
