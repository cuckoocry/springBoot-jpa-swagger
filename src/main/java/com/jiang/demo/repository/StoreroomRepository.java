package com.jiang.demo.repository;

import com.jiang.demo.entity.Storeroom;
import com.jiang.demo.entity.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: 江云飞
 * Date:   2019/4/20
 */
public interface StoreroomRepository extends JpaRepository<Storeroom,Integer>, JpaSpecificationExecutor<Storeroom> {


    @Query(value = "select * from storeroom where goods_id=?;", nativeQuery = true)
    Storeroom findByGoodsId(Integer goodsId);
}
