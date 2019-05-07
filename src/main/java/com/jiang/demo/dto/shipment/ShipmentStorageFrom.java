package com.jiang.demo.dto.shipment;

import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/5/7
 */
public class ShipmentStorageFrom {

    //定单id
    private Integer id;


    //进库时间
    private Date updateTime;

    //进库操作人员
    private String person;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
