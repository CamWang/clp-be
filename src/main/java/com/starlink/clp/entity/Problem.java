package com.starlink.clp.entity;

import com.starlink.clp.constant.DifficultyEnum;
import com.starlink.clp.constant.ProblemEnum;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

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

    // 题面类型
    @Column(columnDefinition = "tinyint unsigned")
    private ProblemEnum type;

    // 题目难度
    @Column(columnDefinition = "tinyint unsigned")
    private DifficultyEnum difficulty;

    // 题目时限 - 以秒为单位
    @Column(columnDefinition = "decimal(6,2)")
    private BigDecimal timeLimit;

    // 内存限制 - 以MB为单位
    @Column(columnDefinition = "int unsigned")
    private Integer memoryLimit;

    // 输出限制 - 以MB为单位
    @Column(columnDefinition = "int unsigned")
    private Integer outputLimit;

    // 问题与判题结果关联
    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private Collection<Submission> submissions = new ArrayList<>();

    // 问题相关解答运行相关脚本
    @ManyToOne(fetch = FetchType.LAZY)
    private Executable runScript;

    // 问题相关解答比较相关脚本
    @ManyToOne(fetch = FetchType.LAZY)
    private Executable compareScript;

    // 问题与问题标签关联
    @ManyToMany(mappedBy = "problems")
    private Collection<Tag> tags = new ArrayList<>();


}
