package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 班级实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:36
 */

@Data
@Entity
public class Clazz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 班级名称
    @Column(columnDefinition = "varchar(128)")
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

}
