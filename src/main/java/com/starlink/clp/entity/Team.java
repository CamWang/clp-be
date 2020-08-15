package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 队伍实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:35
 */

@Data
@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 队伍名称
    @Column(columnDefinition = "varchar(128)")
    private String name;

    // 队伍描述
    @Column(columnDefinition = "varchar(255)")
    private String description;

    // 队伍创建时间
    @Column(columnDefinition = "datetime")
    private Date register;

    // 队员
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<User> users = new ArrayList<>();

    // 队伍所属学校
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private School school;

    // 比赛对应队伍
    @ManyToMany(mappedBy = "teams", cascade = CascadeType.PERSIST)
    private Collection<Contest> contests;

}
