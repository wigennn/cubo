package com.redbyte.platform.authoritycenter.core.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wangwq
 * @since 2021-03-02
 */
@Data
@Accessors(chain = true)
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改人
     */
    private Long modifier;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
