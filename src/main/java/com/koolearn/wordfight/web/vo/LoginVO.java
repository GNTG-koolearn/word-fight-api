package com.koolearn.wordfight.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by chenzhongyong on 2018/08/24.
 */

@Data
@ApiModel(value = "登录对象(unionId)", description = "UnionIdLoginVo")
public class LoginVO implements Serializable{

    @ApiModelProperty(value = "微信code")
    private String code;
    @ApiModelProperty(value = "用户姓名")
    private String nickName;
    @ApiModelProperty(value = "加密数据（base64编码过的字符串）")
    private String encryptedData;
    @ApiModelProperty(value = "加密算法的初始向量（base64编码过的字符串）")
    private String iv;
}
