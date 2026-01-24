package com.wander.vo;

import com.wander.enums.ReturnCodeEnum;

// 统一返回类型
public class ResultData<T> {

    private String code;

    private String msg;

    private T data;

    private Long timestamp;

    public ResultData() {
    }

    public ResultData(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }



    private static class ResultDataBuilder<T> {

        private String code;

        private String msg;

        private T data;

        private ResultDataBuilder() {}

        public static <T> ResultDataBuilder<T> of() {
            return new ResultDataBuilder<T>();
        }

        public ResultDataBuilder<T> code(ReturnCodeEnum code) {
            this.code = code.getCode();
            return this;
        }

        public ResultDataBuilder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }
        public ResultDataBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResultData<T> build() {
            return new ResultData<>(code, msg, data);
        }
    }

    public static <T> ResultDataBuilder<T> builder() {
        return ResultDataBuilder.of();
    }

    // 添加一个简化版的构建方法
    public static <T> ResultData<T> of(String code, String msg, T data) {
        return new ResultData<>(code, msg, data);
    }

    // 成功方法使用Builder
    public static <T> ResultData<T> success(T data) {
        return ResultData.<T>builder()
                .code(ReturnCodeEnum.RC200)
                .msg("success")
                .data(data)
                .build();
    }

    // 警告方法
    public static <T> ResultData<T> warn(String msg) {
        return ResultData.<T>builder()
                .code(ReturnCodeEnum.RC201)
                .msg(msg)
                .build();
    }


    // 错误方法
    public static <T> ResultData<T> error(String msg) {
        return ResultData.<T>builder()
                .code(ReturnCodeEnum.RC500) // 确保ReturnCodeEnum有RC500
                .msg(msg)
                .build();
    }

    public static <T> ResultData<T> error(ReturnCodeEnum code, String msg) {
        return ResultData.<T>builder()
                .code(code)
                .msg(msg)
                .build();
    }
}
