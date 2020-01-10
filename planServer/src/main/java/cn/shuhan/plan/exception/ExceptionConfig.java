package cn.shuhan.plan.exception;

import cn.shuhan.plan.domain.dto.ApiResp;
import cn.shuhan.plan.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理
 */
@Slf4j
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
//        log.error(e.getMessage(), e);
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
//        log.error(e.getMessage(), e);
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
//        log.error(e.getMessage(), e);
        return ApiResp.fail(ResultEnum.LOGIN_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResp usernameNotFoundException(Exception e) {
//        log.error(e.getMessage(), e);
        return ApiResp.fail(ResultEnum.LOGIN_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ApiResp exception(Exception e) {
        log.error(e.getMessage());
        return ApiResp.fail(ResultEnum.SERVICE_EXPIREE);
    }

    @ExceptionHandler(BaseException.class)
    public ApiResp BaseException(BaseException e) {
        log.error(e.getMsg(), e);
        return ApiResp.fail(e);
    }
}
