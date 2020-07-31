package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 班级实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:36
 */

@Data
@Entity
@Component
public class Clazz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    @Column(columnDefinition = "varchar(128)")
    private String name;

    @Column(columnDefinition = "varchar(255)")
    private String description;

}
