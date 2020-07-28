package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 题目实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:51
 */

@Data
@Entity
@Component
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "varchar(128)")
    private String title;

    @Column(columnDefinition = "text")
    private String text;

    @Column(columnDefinition = "varchar(16)")
    private String type;

    @Column(columnDefinition = "float(4,1)")
    private Float time_limit;

    @Column(columnDefinition = "unsigned int")
    private Integer memory_limit;

    @Column(columnDefinition = "default false")
    private Boolean spj;



}
