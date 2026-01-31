package com.wander.entity;

import javax.persistence.*;

/**
 * 表名：t_order
*/
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

    private Integer count;

    private Long money;

    /**
     * 订单状态: 0创建中；1已完结
     */
    private Integer status;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return productId
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return money
     */
    public Long getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(Long money) {
        this.money = money;
    }

    /**
     * 获取订单状态: 0创建中；1已完结
     *
     * @return status - 订单状态: 0创建中；1已完结
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态: 0创建中；1已完结
     *
     * @param status 订单状态: 0创建中；1已完结
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}