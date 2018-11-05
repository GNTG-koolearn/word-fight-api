package com.koolearn.wordfight.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("tb_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField
    private String userName;

    @TableField
    private String uid;

    @TableField
    private String unionId;

    @TableField
    private String avatar;

    @TableField
    private Integer score;

    @TableField
    private Integer attack;

    @TableField
    private Integer defense;

    @TableField
    private Integer reputation;

    @TableField
    private Integer props;

    @TableField
    private Integer totalRounds;

    @TableField
    private Integer winRounds;

    @TableField
    private Integer propCount;

    @TableField(fill = FieldFill.INSERT)
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableLogic
    private Integer deleted;
}