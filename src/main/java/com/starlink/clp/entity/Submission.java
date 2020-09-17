package com.starlink.clp.entity;

import com.starlink.clp.constant.LanguageEnum;
import com.starlink.clp.constant.ResultEnum;
import com.starlink.clp.view.SubmissionCreateView;
import com.starlink.clp.view.SubmissionModifiedView;
import com.starlink.clp.view.SubmissionSecurityView;
import com.starlink.clp.view.TeamSecurityView;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 交题记录实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:29
 */
@Data
@Entity
@Component
public class Submission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    @Range(groups = {SubmissionModifiedView.class}, min = 0, max = 2097152, message = "题解ID范围超限")
    @NotNull(groups = {SubmissionModifiedView.class}, message = "题解ID不能为空")
    private Integer id;

    // 交题时间
    @Column(columnDefinition = "datetime(3)")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Date submit;

    // 解题使用的语言
    @Column(columnDefinition = "tinyint unsigned")
    @NotNull(groups = {SubmissionCreateView.class}, message = "题解语言不能为空")
    @Length(groups = {SubmissionCreateView.class}, min = 1, max = 20, message = "题解语言字符串超界")
    private LanguageEnum language;

    // 解题代码
    @Column(columnDefinition = "text")
    @NotNull(groups = {SubmissionCreateView.class}, message = "题解代码不能为空")
    private String code;

    // 判题开始时间 - 是否为null为判题开始标志
    @Column(columnDefinition = "datetime(3)")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Date start;

    // 判题结束时间
    @Column(columnDefinition = "datetime(3)")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Date end;

    // 判题结果
    @Enumerated
    @Column(columnDefinition = "tinyint unsigned")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private ResultEnum result;

    // 题解运行时间
    @Column(columnDefinition = "int unsigned")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Integer time;

    // 题解运行使用内存
    @Column(columnDefinition = "int unsigned")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Integer memory;

    // 题解相似度 50 - 100
    @Column(columnDefinition = "int unsigned")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Integer sim;

    // 与该题解相似的之前题解ID
    @Column(columnDefinition = "int unsigned")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Integer simId;

    // 题解通过率
    @Column(columnDefinition = "decimal(4,2)")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Float passRate;

    // 判题错误信息
    @Column(columnDefinition = "text")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private String error;

    // 编译信息
    @Column(columnDefinition = "text")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private String compileInfo;

    // 运行信息
    @Column(columnDefinition = "text")
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private String runInfo;

    // 重判题目
    @OneToMany(mappedBy = "submission", fetch = FetchType.LAZY)
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Collection<Rejudge> rejudges = new ArrayList<>();

    // 提交题解的用户
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private User user;

    // 题解所属的比赛
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Contest contest;

    // 题解所属问题
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Null(groups = {SubmissionSecurityView.class}, message = "安全检查失败")
    private Problem problem;

}
