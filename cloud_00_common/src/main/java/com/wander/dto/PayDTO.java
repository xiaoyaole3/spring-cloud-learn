package com.wander.dto;


import java.io.Serializable;
import java.math.BigDecimal;

public class PayDTO implements Serializable {

    private Integer id;

    private String payNo;

    private String orderNo;

    private Integer userId;

    private BigDecimal amount;

    public PayDTO() {
    }

    public PayDTO(Integer id, String payNo, String orderNo, Integer userId, BigDecimal amount) {
        this.id = id;
        this.payNo = payNo;
        this.orderNo = orderNo;
        this.userId = userId;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
