package com.starlink.clp.entity;

import com.starlink.clp.view.ClazzModifiedView;
import com.starlink.clp.view.ClazzRegisterView;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 班级实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:36
 */

@Data
@Entity
@DynamicUpdate(value = true)
public class Clazz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    @Range(min = 0, max = 2097152, message = "班级ID范围超限")
    @NotNull(groups = {ClazzModifiedView.class}, message = "班级ID不能为空")
    private Integer id;

    // 班级名称
    @Column(columnDefinition = "varchar(128)")
    @NotEmpty(groups = {ClazzRegisterView.class, ClazzModifiedView.class}, message = "班级名称不能为空")
    @Length(groups = {ClazzRegisterView.class, ClazzModifiedView.class}, min = 2, max = 32, message = "班级名称长度在2-32字符之间")
    private String name;

    // 班级描述
    @Column(columnDefinition = "varchar(255)")
    private String description;

    // 班级用户
    @OneToMany(mappedBy = "clazz", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Collection<User> user = new ArrayList<>();

    // 班级学校
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private School school;

    public Clazz() {}

    /**
     * 注册班级所用的构造函数
     * 在注册中不需要设置学校
     */
    public Clazz(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * 修改班级信息所用的构造函数
     * 在修改信息中设置学校
     */

    public Clazz(Integer id,
                 String name,
                 String description,
                 Collection<User> user,
//                 School school
                 Integer schoolId
    ) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.description = description;
        this.school.setId(schoolId);
//        this.school = school;
    }
}
