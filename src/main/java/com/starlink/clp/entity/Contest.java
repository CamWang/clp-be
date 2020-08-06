package com.starlink.clp.entity;

import com.starlink.clp.constant.ContestEnum;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 比赛实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:38
 */

@Data
@Entity
@Component
public class Contest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 比赛标题
    @Column(columnDefinition = "varchar(128)")
    private String title;

    // 比赛描述
    @Column(columnDefinition = "text")
    private String description;

    // 比赛罚时
    @Column(columnDefinition = "tinyint unsigned")
    private Integer penalty;

    // 比赛类型
    @Column(columnDefinition = "tinyint unsigned")
    private ContestEnum type;

    // 是否向用户展示错误
    @Column(columnDefinition = "tinyint unsigned")
    private Boolean error;

    // 比赛开始时间
    @Column(columnDefinition = "datetime")
    private Date start;

    // 比赛结束时间
    @Column(columnDefinition = "datetime")
    private Date end;

    // 比赛封榜时间
    @Column(columnDefinition = "datetime")
    private Date latch;

    // 比赛终止时间
    @Column(columnDefinition = "datetime")
    private Date finish;

    // 比赛是否启用
    @Column(columnDefinition = "tinyint unsigned")
    private Boolean enabled;

    // 比赛与提交题解关联
    @OneToMany(mappedBy = "contest", fetch = FetchType.LAZY)
    private Collection<Submission> submissions = new ArrayList<>();

    // 比赛与气球关联
    @OneToMany(mappedBy = "contest", fetch = FetchType.LAZY)
    private Collection<Balloon> balloons = new ArrayList<>();

    // 比赛与站内信关联
    @OneToMany(mappedBy = "contest", fetch = FetchType.LAZY)
    private Collection<Message> messages = new ArrayList<>();

    // 参加比赛的所有用户
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_contest",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private Collection<User> users;

    // 参加比赛的所有队伍
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "team_contest",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private Collection<Team> teams;

    // 参加比赛的所有学校
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "school_contest",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private Collection<School> schools;
}
