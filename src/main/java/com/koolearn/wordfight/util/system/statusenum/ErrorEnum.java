package com.koolearn.wordfight.util.system.statusenum;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenzhongyong
 */
public enum ErrorEnum {

    WECHAT_LOGIN_ERROR(10001,"微信登录失败"),

    NO_USER(30001, "没有该用户"),
    USER_SAVE_ERROR(30002, "用户保存失败"),
    USER_UPDATE_ERROR(30003, "用户信息更新失败"),

    NO_INVITER(4001, "没有查询到挑战者"),
    NO_ACCEPTER(4002, "没有查询到应战者"),


    NO_ROOMID_ERRROR(5001,"没有房间号"),
    NO_ENOUGH_PROPS(5002,"没有足够的道具了"),
    TOO_MUCH_PROPS(5003,"超过道具最大容量了,默认为5"),
    SYSTEM_ERROR(9000,"系统未知异常");


    private Integer code;
    private String name;

    ErrorEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer code){
        for(ErrorEnum errorEnum : ErrorEnum.values()){
            if(errorEnum.code.equals(code)){
                return errorEnum.name;
            }
        }
        return null;
    }


    public static ErrorEnum getByCode(Integer code){
        for(ErrorEnum productLineEnum : ErrorEnum.values()){
            if(productLineEnum.code.equals(code)){
                return productLineEnum;
            }
        }
        return null;
    }

    public String getName(){
        return name;
    }

    public Integer getCode(){
        return code;
    }
}
