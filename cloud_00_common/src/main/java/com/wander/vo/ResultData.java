package com.wander.vo;

import com.wander.enums.ReturnCodeEnum;

// 统一返回类型
public class ResultData<T> {

    private String code;

    private String msg;

    private T data;

    private Long timestamp;

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

    private static final class ResultDataBuilder<T> {

        private String code;

        private String msg;

        private T data;

        private ResultDataBuilder() {}

        public static ResultDataBuilder builder() {
            return new ResultDataBuilder<>();
        }

        public ResultDataBuilder<T> code(String code) {
            this.code = code;
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

    public static <T> ResultData<T> success(T data) {
        return new ResultDataBuilder<T>()
                .code(ReturnCodeEnum.RC200.getCode())
                .data(data)
                .build();
    }
}
