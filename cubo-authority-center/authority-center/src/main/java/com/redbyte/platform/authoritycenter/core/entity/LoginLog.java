package com.redbyte.platform.authoritycenter.core.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登陆日志表
 * </p>
 *
 * @author wangwq
 * @since 2021-03-02
 */
@Data
@Accessors(chain = true)
@TableName("login_log")
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 用户姓名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户IP地址
     */
    @TableField("ip_address")
    private String ipAddress;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 登录时间
     */
    @TableField("login_time")
    private Date loginTime;

    /**
     * 终端设备
     */
    private Integer terminal;

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

    /**
     * 操作记录
     */
    @TableField("operation_record")
    private String operationRecord;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
