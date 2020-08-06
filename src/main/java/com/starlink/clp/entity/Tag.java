package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 题目/比赛等实体的标签
 *
 * @author CamWang
 * @since 2020/7/30 11:04
 */

@Data
@Entity
@Component
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 分类 - 是给题目用还是比赛用
    @Column(columnDefinition = "varchar(32)")
    private String category;

    // 标签显示内容
    @Column(columnDefinition = "varchar(32)")
    private String content;

    // 标签颜色
    @Column(columnDefinition = "varchar(32)")
    private String color;

    // 标签图标
    @Column(columnDefinition = "varchar(32)")
    private String icon;

    // 与题目关联的标签
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "problem_tag",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private Collection<Problem> problems = new ArrayList<>();

}
