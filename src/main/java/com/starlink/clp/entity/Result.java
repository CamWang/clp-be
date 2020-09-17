package com.starlink.clp.entity;

import com.starlink.clp.constant.ResultEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author CamWang
 * @since 2020/9/13 15:33
 *
 * 给判题机作为每个题目的单组测试数据结果
 */
@Entity
@Data
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer count;

    private ResultEnum resultEnum;

    private String detail;

    private Integer memoryCost;

    private Integer timeCost;
}
