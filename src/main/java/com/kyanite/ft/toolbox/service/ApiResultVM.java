package com.kyanite.ft.toolbox.service;

import java.io.Serializable;

/**
 *service层返回对象列表封装
 * @param <T>
 */
public class ApiResultVM<T>  implements Serializable {

    public static String ERROR_CODE = "-1";

    private boolean success = false;

    private String code;

    private String message;

    private T result;

    public ApiResultVM() {
    }

    public static <T> ApiResultVM<T> success(T result) {
        ApiResultVM<T> item = new ApiResultVM<T>();
        item.success = true;
        item.result = result;
        item.code = "0";
        item.message = "success";
        return item;
    }

    public static <T> ApiResultVM<T> failure(String errorMessage) {
        ApiResultVM<T> item = new ApiResultVM<T>();
        item.success = false;
        item.code = ERROR_CODE;
        item.message = errorMessage;
        return item;
    }



    public boolean hasResult() {
        return result != null;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResult() {
        return result;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
