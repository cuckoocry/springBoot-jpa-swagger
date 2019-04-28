package com.jiang.demo.service;

import com.jiang.demo.utils.PageDTO;
import com.jiang.demo.dto.goods.GoodsDTO;
import com.jiang.demo.dto.goods.GoodsForm;

/**
 * Author: 江云飞
 * Date:   2019/4/6
 */
public interface GoodsService {

    GoodsDTO insertGoods(GoodsForm goodsForm,String person);
    PageDTO<GoodsDTO> findByDynamicCases(GoodsForm goodsForm, Integer pageNum, Integer pageSize);
    GoodsDTO findGoodsDTOById(Integer id);
}
