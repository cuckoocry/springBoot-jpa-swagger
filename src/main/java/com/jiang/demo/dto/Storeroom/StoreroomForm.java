package com.jiang.demo.dto.Storeroom;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */
public class StoreroomForm {

    private Integer pageNum;
    private Integer pageSize;
    @ApiModelProperty(value = "库存量(查询)")
    private Integer amount;

    @ApiModelProperty(value = "商品编号(查询)")
    private  String goodsCode;

    @ApiModelProperty(value = "商品名称(查询)")
    private  String goodsName;


    @ApiModelProperty(value = "更新人员(查询)")
    private String person;

    @ApiModelProperty(value = "库房更新时间上限(查询)")
    private Date startTime;

    @ApiModelProperty(value = "库房更新时间下限(查询)")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }



    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
