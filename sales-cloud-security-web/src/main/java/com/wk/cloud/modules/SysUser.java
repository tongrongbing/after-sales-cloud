package com.wk.cloud.modules;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/13 11:17 上午
 * @description：
 */
@Data
public class SysUser implements Serializable, UserDetails {

    @TableId(type = IdType.AUTO )
    private Integer id;

    private String username;

    private String password; // 密码需要加密后存储

    private String nickName;

    private String mobile;

    private int accountExpired;

    private int accountLocked;

    private int accountEnable;

    @TableField(exist = false) // 表示：authorities不是表中字段
    private Collection<? extends GrantedAuthority> authorities;

    private Date CreateTime;

    private Date updateTime;

    @TableField(exist = false)
    private List<SysRole> roleList = Lists.newArrayList();

    @TableField(exist = false)
    private List<Integer> roleIds =  Lists.newArrayList();

    private List<Integer> getRoleIds(){
        if (CollectionUtils.isNotEmpty(roleList)) {
            roleIds = roleList.stream().map(SysRole::getId).collect(Collectors.toList());
        }
        return roleIds;
    }

    @TableField(exist = false)
    private List<SysPermission> permissions = Lists.newArrayList();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
