package com.example.solon.template.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Title:请求返回的Json包装类
 * Description:请求返回的Json包装类
 *
 * @author trifolium
 * @version 1.0
 */
@Data
@ApiModel(value = "ResponseResult<T>", description = "响应结果包装器")
public class ResponseResult<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private String message = "";

    private boolean success;

    @ApiModelProperty("业务响应码：0正常，1失败，-1异常，其他请参考文档")
    private int code;

    /**
     * 返回成功结果
     */
    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setData(data);
        result.success = true;
        result.code = ResultCode.SUCCESS.value;
        return result;
    }

    /**
     * 成功结果
     */
    public static <T> ResponseResult<T> success() {
        ResponseResult<T> result = new ResponseResult<>();
        result.success = true;
        result.code = ResultCode.SUCCESS.value;
        return result;
    }

    /**
     * 成功结果
     */
    public static <T> ResponseResult<T> success(T data, String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.success = true;
        result.data = data;
        result.message = message;
        result.code = ResultCode.SUCCESS.value;
        return result;
    }

    /**
     * 返回失败的结果
     */
    public static <T> ResponseResult<T> failure(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setMessage(message);
        result.setCode(ResultCode.FAILURE.value);
        result.success = false;
        return result;
    }

    /**
     * 返回失败的结果
     */
    public static <T> ResponseResult<T> failure(String message, int code) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setMessage(message);
        result.setCode(code);
        result.success = false;
        return result;
    }

    /**
     * 异常结果
     */
    public static <T> ResponseResult<T> error(String message) {
        ResponseResult<T> result = new ResponseResult<>();
        result.success = false;
        result.setCode(ResultCode.EXCEPTION.getValue());
        result.setMessage(message);
        return result;
    }

    /**
     * 返回异常结果
     */
    public static <T> ResponseResult<T> error(String message, int code) {
        ResponseResult<T> result = new ResponseResult<>();
        result.setMessage(message);
        result.setCode(code);
        result.success = false;

        return result;
    }

}
