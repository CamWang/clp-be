package com.starlink.clp.entity;

import com.starlink.clp.view.SchoolModifiedView;
import com.starlink.clp.view.SchoolRegisterView;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(groups = {SchoolModifiedView.class}, message = "学校ID不能为空")
    @Range(min = 0, max = 2097152, message = "学校ID范围超限")
    private Integer id;

    // 学校名称
    @Column(columnDefinition = "varchar(128)")
    @NotEmpty(groups = {SchoolRegisterView.class, SchoolModifiedView.class}, message = "学校名字不能为空")
    @Length(groups = {SchoolRegisterView.class, SchoolModifiedView.class}, min = 3, max = 24, message = "学校名字在3-24字符之间")
    private String name;

    // 学校描述
    @Column(columnDefinition = "varchar(255)")
    @NotEmpty(groups = {SchoolRegisterView.class, SchoolModifiedView.class}, message = "学校描述不能为空")
    @Length(groups = {SchoolRegisterView.class, SchoolModifiedView.class}, min = 5, max = 255, message = "学校描述在10-255字符之间")
    private String description;

    // 学校图片
    @Column(columnDefinition = "varchar(255)")
    @NotEmpty(groups = {SchoolModifiedView.class}, message = "学校头像不能为空")
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

    public School() {
    }

    public School(Integer id,
                  String name,
                  String description,
                  String avatar
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
    }

}
