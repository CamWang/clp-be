package com.starlink.clp.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 学校实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:37
 */

@Data
@Entity
public class School implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 学校名称
    @Column(columnDefinition = "varchar(128)")
    @NotEmpty(message = "学校名字不能为空")
    @Length(min = 3,max = 24,message = "学校名字在3-24字符之间")
    private String name;

    // 学校描述
    @Column(columnDefinition = "varchar(255)")
    @NotEmpty(message = "学校描述不能为空")
    @Length(min = 10,max = 255,message = "学校描述在10-255字符之间")
    private String description;

    // 学校图片
    @Column(columnDefinition = "varchar(255)")
    private String avatar;

    // 用户学校关联
    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<User> user = new ArrayList<>();

    // 班级学校关联
    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<Clazz> clazzes = new ArrayList<>();

    // 队伍学校关联
    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<Team> teams = new ArrayList<>();

    // 比赛学校关联
    @ManyToMany(mappedBy = "schools", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<Contest> contests;

}
