package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 重判任务实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:44
 */

@Data
@Entity
public class Rejudge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 重判开始时间 - 是否为null是是否开始判题标志
    @Column(columnDefinition = "datetime(3)")
    private Date start;

    // 重判结束时间
    @Column(columnDefinition = "datetime(3)")
    private Date end;

    // 重判与题解的关联
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Submission submission;


}
