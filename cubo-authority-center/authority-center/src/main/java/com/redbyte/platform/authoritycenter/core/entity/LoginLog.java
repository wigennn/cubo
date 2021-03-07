package com.redbyte.platform.authoritycenter.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 登陆日志表
 * </p>
 *
 * @author wangwq
 * @since 2021-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户IP地址
     */
    private String ipAddress;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 终端设备
     */
    private Integer terminal;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 操作记录
     */
    private String operationRecord;


}
