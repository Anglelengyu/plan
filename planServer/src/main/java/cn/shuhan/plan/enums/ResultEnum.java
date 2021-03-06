package cn.shuhan.plan.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {
    //成功 200-
    OK(200,"成功"),

    //权限 300-
    NO_PERMISSION(403,"没有权限"),

    //资源400-
    NOT_FOUND(404,"数据不存在"),

    //失败500-
    ERROR(501,"失败"),
    LOGIN_ERROR(502,"登录失败,用户名或密码错误"),
    TOKEN_EXPIREE(503,"token失效"),
    SERVICE_EXPIREE(503,"系统错误，请联系管理员"),
    PAGINATION_ERROR(504, "分页信息错误"),

    NO_LOGIN(999,"未登录"),
    LOGOUT(998,"退出登录"),



    // 基础错误 1000+
    NO_DATA(1000,"数据不存在，请核实"),
    ;

    public Integer code;
    public String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code){
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (resultEnum.code.compareTo(code) == 0) {
                return resultEnum.msg;
            }
        }
        return null;
    }
}
