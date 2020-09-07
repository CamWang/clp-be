package com.starlink.clp.entity;

import com.starlink.clp.view.TeamModifiedView;
import com.starlink.clp.view.TeamRegisterView;
import com.starlink.clp.view.TeamSecurityView;
import com.starlink.clp.view.UserRegisterView;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    @Null(groups = {TeamRegisterView.class}, message = "注册时不能携带ID")
    @Range(min = 0, max = 2097152, message = "队伍ID范围超限")
    @NotNull(groups = {TeamModifiedView.class}, message = "队伍ID不能为空")
    private Integer id;

    // 队伍名称
    @Column(columnDefinition = "varchar(128)")
    @NotEmpty(groups = {TeamRegisterView.class, TeamModifiedView.class}, message = "队伍名称不能为空")
    @Length(groups = {TeamRegisterView.class, TeamModifiedView.class},min=2, max=32,  message = "队伍名称在2-32字符之间")
    private String name;

    // 队伍描述
    @Column(columnDefinition = "varchar(255)")
    private String description;

    // 队伍创建时间
    @Column(columnDefinition = "datetime")
    @Null(groups = {TeamSecurityView.class}, message = "安全检查失败")
    private Date register;

    // 队员
    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Null(groups = {TeamSecurityView.class}, message = "安全检查失败")
    private Collection<User> users = new ArrayList<>();

    // 队伍所属学校
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Null(groups = {TeamSecurityView.class}, message = "安全检查失败")
    private School school;

    // 比赛对应队伍
    @ManyToMany(mappedBy = "teams", cascade = CascadeType.PERSIST)
    @Null(groups = {TeamSecurityView.class}, message = "安全检查失败")
    private Collection<Contest> contests;

}
