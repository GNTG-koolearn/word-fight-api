package com.koolearn.wordfight.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/27
 * Time: 15:11
 */
@Data
@ApiModel(value = "能力图谱", description = "能力图谱")
public class PowerMapVO implements Serializable {

    @ApiModelProperty(value = "进攻")
    private Integer attack;
    @ApiModelProperty(value = "防守")
    private Integer defense;
    @ApiModelProperty(value = "道具")
    private Integer props;
    @ApiModelProperty(value = "声望")
    private Integer reputation;
    @ApiModelProperty(value = "胜率")
    private Integer winRate;
}
