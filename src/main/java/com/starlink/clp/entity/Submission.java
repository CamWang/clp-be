package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Integer id;

    // 交题时间
    @Column(columnDefinition = "datetime(3)")
    private Date submit;

    // 解题代码
    @Column(columnDefinition = "text")
    private String code;

    // 判题开始时间 - 是否为null为判题开始标志
    @Column(columnDefinition = "datetime(3)")
    private Date start;

    // 判题结束时间
    @Column(columnDefinition = "datetime(3)")
    private Date end;

    // 判题结果
    @Column(columnDefinition = "varchar(32)")
    private String result;

    // 判题错误信息
    @Column(columnDefinition = "text")
    private String error;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
