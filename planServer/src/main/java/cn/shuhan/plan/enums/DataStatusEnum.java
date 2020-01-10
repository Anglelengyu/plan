package cn.shuhan.plan.enums;


public enum DataStatusEnum {
    NORMAL(1, "正常"),
    DELETED(0, "删除");


    public Integer code;
    public String msg;

    DataStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(Integer code){
        for (DataStatusEnum resultEnum : DataStatusEnum.values()) {
            if (resultEnum.code.compareTo(code) == 0) {
                return resultEnum.msg;
            }
        }
        return null;
    }
}
