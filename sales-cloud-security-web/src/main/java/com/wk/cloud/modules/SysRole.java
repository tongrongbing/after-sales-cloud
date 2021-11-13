package com.wk.cloud.modules;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author：tongrongbing
 * @date：created in 2021/11/13 11:32 上午
 * @description：
 */
@Data
public class SysRole {

    @TableId(type = IdType.AUTO )
    private Integer id;

    private String name;

    private String remark;

    private Date CreateTime;

    private Date updateTime;

    @TableField(exist = false)
    private List<SysPermission> perList = Lists.newArrayList();

    @TableField(exist = false)
    private List<Integer> perIds =  Lists.newArrayList();

    private List<Integer> getPerIds(){
        if (CollectionUtils.isNotEmpty(perList)) {
            perIds = perList.stream().map(SysPermission::getId).collect(Collectors.toList());
        }
        return perIds;
    }

}
