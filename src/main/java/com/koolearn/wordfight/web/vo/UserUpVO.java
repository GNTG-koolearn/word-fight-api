package com.koolearn.wordfight.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/6
 * Time: 11:43
 */
@Data
@ApiModel(value = "用户更新对象", description = "用户更新对象")
public class UserUpVO {

    @ApiModelProperty(value = "用户ID")
    private String uid;

    @ApiModelProperty(value = "真实姓名")
    private String name;
}
