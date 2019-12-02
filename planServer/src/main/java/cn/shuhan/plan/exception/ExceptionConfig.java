package cn.shuhan.plan.exception;

import cn.shuhan.plan.domain.dto.ApiResp;
import cn.shuhan.plan.enums.ResultEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理
 */
@RestControllerAdvice
public class ExceptionConfig  {
    /**
     * 找不到资源 -> com.zzx.config.ErrorConfig
     * 未找到处理器 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResp noHandlerFoundExceptionHander(Exception e) {
//        e.printStackTrace();
        return ApiResp.fail(ResultEnum.NOT_FOUND);
    }

    /**
     * 权限不足
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResp accessDeniedExceptionHander(Exception e) {
        return ApiResp.fail(ResultEnum.NO_PERMISSION);
    }

    /**
     * 权限不足
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ApiResp badCredentialsException(Exception e) {
        return ApiResp.fail(ResultEnum.LOGIN_ERROR);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResp usernameNotFoundException(Exception e) {
        return ApiResp.fail(ResultEnum.LOGIN_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ApiResp exception(Exception e) {
        return ApiResp.fail(ResultEnum.SERVICE_EXPIREE);
    }
}
