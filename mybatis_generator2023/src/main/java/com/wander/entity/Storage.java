package com.wander.entity;

import javax.persistence.*;

/**
 * 表名：t_storage
*/
@Table(name = "t_storage")
public class Storage {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    private Integer total;

    private Integer used;

    private Integer residue;

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
     * @return total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return used
     */
    public Integer getUsed() {
        return used;
    }

    /**
     * @param used
     */
    public void setUsed(Integer used) {
        this.used = used;
    }

    /**
     * @return residue
     */
    public Integer getResidue() {
        return residue;
    }

    /**
     * @param residue
     */
    public void setResidue(Integer residue) {
        this.residue = residue;
    }
}