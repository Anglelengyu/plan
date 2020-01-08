package cn.shuhan.plan.exception;

import cn.shuhan.plan.domain.dto.ApiResp;
import cn.shuhan.plan.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.MessageFormat;

/**
 * 服务基础异常类，所有服务的需要抛到controller层的自定义异常，都需要继承该类
 * @author: fenguangjing
 * @review:
 * @date: 2018/6/28/0028 15:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    /** 错误码 */
    private Integer code;

    /** 错误信息 */
    private String msg;

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 除非工具类等，一般不使用该方法
     */
    public static BaseException build(Integer code, String msg) {
        return new BaseException(code, msg);
    }

    public static BaseException build(ResultEnum resultEnum) {
        return new BaseException(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static BaseException build(ResultEnum resultEnum, Object... strings) {
        return new BaseException(resultEnum.getCode(), MessageFormat.format(resultEnum.getMsg(), strings));
    }

    public static BaseException build(ApiResp apiResp) {
        return new BaseException(apiResp.getCode(), apiResp.getMsg());
    }
}
