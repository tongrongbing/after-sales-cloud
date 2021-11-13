package com.wk.cloud.modules;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/13 11:36 上午
 * @description：
 */
@Data
public class SysPermission implements Serializable {

    @TableId(type = IdType.AUTO )
    private Integer id;

    private Integer parentId = 0;

    @TableField(exist = false)
    private String parentName = "根菜单";

    private String name;

    private String code;

    private String url;

    private Integer type;

    private String icon;

    private String remark;

    private Date CreateTime;

    private Date updateTime;

    @TableField(exist = false)
    private List<SysPermission> children;

    @TableField(exist = false)
    private List<String> childrenUrl;

}
