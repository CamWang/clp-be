package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 题目实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:51
 */

@Data
@Entity
@Component
public class Problem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 题目标题
    @Column(columnDefinition = "varchar(128)")
    private String title;

    // 题面
    @Column(columnDefinition = "text")
    private String text;

    // 题目类型
    @Column(columnDefinition = "varchar(16)")
    private String type;

    // 题目难度
    @Column(columnDefinition = "int")
    private Integer difficulty;

    // 题目时限 - 以秒为单位
    @Column(columnDefinition = "float(4,1)")
    private Float timeLimit;

    // 内存限制 - 以MB为单位
    @Column(columnDefinition = "int unsigned")
    private Integer memoryLimit;

    // 输出限制 - 以MB为单位
    @Column(columnDefinition = "int unsigned")
    private Integer outputLimit;


}
