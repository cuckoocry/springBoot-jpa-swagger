package com.jiang.demo.controller;

import com.jiang.demo.dto.purchase.PurchaseDTO;
import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.service.PurchaseDetailService;
import com.jiang.demo.service.PurchaseService;
import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */

@RestController
@Api(description = "商品进货" )   //swagger
@RequestMapping("/purchase")
public class PurchaseController {
    // 通过set方法注入  优先选择
    private PurchaseService purchaseService;
    @Autowired
    public void setPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

   private PurchaseDetailService purchaseDetailService;
    @Autowired
    public void setPurchaseDetailService(PurchaseDetailService purchaseDetailService) {
        this.purchaseDetailService = purchaseDetailService;
    }

    /*提交订单详情的集合  以及订单*/
    //@RequestBody
    @ApiOperation(value = "采购商品")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")
    public Result<List<PurchaseDetailDTO>> insertPurchase(PurchaseForm purchaseForm){

        //然后添加订单详情
        List<PurchaseDetailDTO> purchaseDetailDTOList = purchaseDetailService.insertPurchaseDetail(purchaseForm);

        return ResultUtil.success(purchaseDetailDTOList);
    }

    @ApiOperation(value = "采购单动态分页显示")
    @PostMapping("/select")
    @SuppressWarnings("unchecked")
    //动态分页查询（编号，时间，姓名）
    public Result<PageDTO<PurchaseDTO>> select(PurchaseForm purchaseForm,Integer pageNum,Integer pageSize){
        PageDTO<PurchaseDTO> select = purchaseService.select(purchaseForm, pageNum, pageSize);

        return ResultUtil.success(select);
    }

    @ApiOperation(value = "采购单显示")
    @PostMapping("/selectAll")
    @SuppressWarnings("unchecked")
    //动态分页查询（编号，时间，姓名）
    public Result<List<PurchaseDTO>> selectAll(){
        List<PurchaseDTO> all = purchaseService.findAll();
        return ResultUtil.success(all);
    }

}