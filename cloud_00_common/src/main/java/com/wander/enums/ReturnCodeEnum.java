package com.wander.enums;

import java.util.Arrays;

public enum ReturnCodeEnum {
    // 业务自定义错误
    RC999("999", "系统错误，操作XXX失败"),

    // HTTP 2xx
    RC200("200", "success"),
    RC201("201", "服务开启降级保护，请稍后再试"),
    RC202("202", "热点参数限流，请稍后再试"),
    RC203("203", "系统规则不满足要求，请稍后再试"),
    RC204("204", "授权规则不通过，请稍后再试"),

    // HTTP 4xx
    RC401("401", "匿名用户无权限访问"),
    RC403("403", "无访问权限，请联系管理员授予权限"),

    // HTTP 5xx 系统内部
    RC500("500", "系统异常，请稍后重试"),

    INVALID_TOKEN("2001", "访问令牌不合法"),
    ACCESS_DENIED("2002", "没有权限访问该资源"),
    CLIENT_AUTHENTICATION_FAILED("1001", "客户端认证失败"),
    USER_OR_PASSWORD_ERROR("1002", "用户名或密码错误"),
    BUSINESS_ERROR("1003", "业务逻辑异常"),
    UNSUPPORTED_GRANT_TYPE("1004", "不支持的认证模式"),

    INVALID("9999", "无效的返回码")
    ;

    private final String code;
    private final String message;

    ReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public static ReturnCodeEnum getByCode(String code) {
        return Arrays.stream(ReturnCodeEnum.values())
                .filter(returnCodeEnum -> returnCodeEnum.getCode().equals(code))
                .findFirst()
                .orElse(ReturnCodeEnum.INVALID);
    }
}
