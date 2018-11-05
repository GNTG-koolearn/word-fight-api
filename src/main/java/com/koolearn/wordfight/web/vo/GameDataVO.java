package com.koolearn.wordfight.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 * Date: 2018/9/29
 * Time: 14:49
 */
@Data
@ApiModel(value = "玩家游戏数据对象", description = "用户更新对象")
public class GameDataVO {

    @ApiModelProperty(value = "用户ID")
    String uid;
    @ApiModelProperty(value = "pk类型")
    int pkType;
    @ApiModelProperty(value = "是否获胜")
    int isWin;
    @ApiModelProperty(value = "用户得分")
    int score;
    @ApiModelProperty(value = "房间号")
    String roomId;
    @ApiModelProperty(value = "实用道具数")
    int propCount;
}
