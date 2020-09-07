package com.starlink.clp.entity;

import com.starlink.clp.constant.DifficultyEnum;
import com.starlink.clp.constant.ProblemEnum;
import com.starlink.clp.view.ProblemCreateView;
import com.starlink.clp.view.ProblemModifiedView;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
public class Problem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    @Null(groups = {ProblemCreateView.class}, message = "新增时不能传入ID")
    @NotNull(groups = {ProblemModifiedView.class}, message = "学校ID不能为空")
    @Range(groups = {ProblemModifiedView.class}, message = "问题ID范围超限")
    private Integer id;

    // 题目标题
    @Column(columnDefinition = "varchar(128)")
    @NotEmpty(groups = {ProblemCreateView.class, ProblemModifiedView.class}, message = "题目标题不能为空")
    @Length(groups = {ProblemCreateView.class, ProblemModifiedView.class},min = 1, max = 120, message = "题目标题长度在1-120字符之间")
    private String title;

    // 题面，如果是PDF等二进制文件格式则存储储存路径
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
    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<Submission> submissions = new ArrayList<>();

    // 问题相关解答运行相关脚本
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Executable runScript;

    // 问题相关解答比较相关脚本
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Executable compareScript;

    // 问题与问题标签关联
    @ManyToMany(mappedBy = "problems", cascade = CascadeType.PERSIST)
    private Collection<Tag> tags = new ArrayList<>();


}
