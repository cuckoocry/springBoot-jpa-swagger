package com.jiang.demo.controller;

import com.jiang.demo.dto.purchase.PurchaseDTO;
import com.jiang.demo.dto.purchase.PurchaseForm;
import com.jiang.demo.dto.purchaseDetail.PurchaseDetailDTO;
import com.jiang.demo.dto.supplier.SupplierDTO;
import com.jiang.demo.dto.supplier.SupplierForm;
import com.jiang.demo.service.PurchaseDetailService;
import com.jiang.demo.service.PurchaseService;
import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.utils.Result;
import com.jiang.demo.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/21
 */

@RestController
@Api(description = "采购单管理" )   //swagger
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
    @ApiOperation(value = "生成采购单")
    @PostMapping("/insert")
    @SuppressWarnings("unchecked")
    public Result<List<PurchaseDetailDTO>> insertPurchase(@RequestBody PurchaseForm purchaseForm){

        //然后添加订单详情
        List<PurchaseDetailDTO> purchaseDetailDTOList = purchaseDetailService.insertPurchaseDetail(purchaseForm);

        return ResultUtil.success(purchaseDetailDTOList);
    }

    @ApiOperation(value = "采购单动态分页显示")
    @PostMapping("/select")
    @SuppressWarnings("unchecked")
    //动态分页查询（编号，时间，姓名）
    public Result<PageDTO<PurchaseDTO>> select(@RequestBody PurchaseForm purchaseForm){
        PageDTO<PurchaseDTO> select = purchaseService.select(purchaseForm);

        return ResultUtil.success(select);
    }

    /*@ApiOperation(value = "更新采购单")
    @PutMapping("/update")
    @SuppressWarnings("unchecked")
    public Result update(Integer ){

        //return supplierDTO;
        return ResultUtil.success();
    }*/

}
