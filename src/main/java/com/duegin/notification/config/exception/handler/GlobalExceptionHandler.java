package com.duegin.notification.config.exception.handler;

import com.duegin.notification.config.Result;
import com.duegin.notification.config.exception.BusinessException;
import com.duegin.notification.constant.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author DueGin
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> validatedExceptionHandler(ConstraintViolationException e){
        log.error(String.valueOf(e), e);
        return Result.ok(ResultEnum.PARAMETER_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result<Void> businessExceptionHandle(BusinessException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return Result
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Void> parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        log.error(String.valueOf(e), e);
        return Result.ok(ResultEnum.PARAMETER_ERROR.getCode(), "请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return Result
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Void> parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        log.error(String.valueOf(e), e);
        return Result.ok(ResultEnum.PARAMETER_ERROR.getCode(), "请求体不能为空");
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return ResponseInfo
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> parameterExceptionHandler(MethodArgumentNotValidException e) {
        log.error(String.valueOf(e), e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return Result.ok(ResultEnum.PARAMETER_ERROR.getCode(), fieldError.getDefaultMessage());
            }
        }
        return Result.ok(ResultEnum.PARAMETER_ERROR);
    }

    @ExceptionHandler(LoginException.class)
    public Result<Void> loginExceptionHandle(LoginException e){
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }
}
