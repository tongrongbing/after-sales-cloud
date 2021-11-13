package com.wk.cloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wk.cloud.modules.SysUser;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/13 11:50 上午
 * @description：
 */
public interface SysUserService extends IService<SysUser> {

    SysUser findUserByName(String username);
}
