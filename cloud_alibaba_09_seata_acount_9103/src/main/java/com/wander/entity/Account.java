package com.wander.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：t_account
*/
@Table(name = "t_account")
public class Account {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private Long total;

    private Long used;

    private Long residue;

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
     * @return total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * @return used
     */
    public Long getUsed() {
        return used;
    }

    /**
     * @param used
     */
    public void setUsed(Long used) {
        this.used = used;
    }

    /**
     * @return residue
     */
    public Long getResidue() {
        return residue;
    }

    /**
     * @param residue
     */
    public void setResidue(Long residue) {
        this.residue = residue;
    }
}