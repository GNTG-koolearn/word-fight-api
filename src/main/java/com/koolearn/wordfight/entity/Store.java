package com.koolearn.wordfight.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 题目表
 * </p>
 *
 * @author chenzhongyong
 * @since 2018-09-27
 */
@Data
@TableName("tb_store")
@ApiModel(value="题目对象", description="题目表")
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题目id")
    @TableId(value = "id",type = IdType.INPUT)
    private Long storeId;

    @ApiModelProperty(value = "单词id")
    @TableField
    private Integer wordId;

    @ApiModelProperty(value = "题目类型")
    @TableField
    private Integer questionType;

    @ApiModelProperty(value = "题目标题")
    @TableField
    private String questionTitle;

    @ApiModelProperty(value = "选项一")
    @TableField
    private String optionA;

    @ApiModelProperty(value = "选项二")
    @TableField
    private String optionB;

    @ApiModelProperty(value = "选项三")
    @TableField
    private String optionC;

    @ApiModelProperty(value = "选项四")
    @TableField
    private String optionD;

    @ApiModelProperty(value = "正确选项答案")
    @TableField
    private String trueOption;

    @ApiModelProperty(value = "单词")
    @TableField
    private String word;

    @ApiModelProperty(value = "音频url")
    @TableField
    private String audioAddress;


}
