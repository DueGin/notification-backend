package com.duegin.notification.config;

import com.duegin.notification.constant.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author DueGin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public static Result<Void> ok() {
        return new Result<>(ResultEnum.SUCCESS.getCode(), "操作成功", null);
    }

    public static Result<Void> ok(String msg) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), msg, null);
    }


    public static Result<Void> ok(ResultEnum resultEnum) {
        return new Result<>(resultEnum.getCode(), resultEnum.getMsg(), null);
    }

    public static Result<Void> ok(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), "操作成功", data);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(ResultEnum.ERROR.getCode(), msg, null);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResultEnum.ERROR.getCode(), "操作失败", null);
    }
}
