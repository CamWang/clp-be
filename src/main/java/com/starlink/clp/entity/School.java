package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 学校实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:37
 */

@Data
@Entity
@Component
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "varchar(128)")
    private String name;

    @Column(columnDefinition = "varchar(255)")
    private String description;

    @Column(columnDefinition = "varchar(255)")
    private String avatar;
}
