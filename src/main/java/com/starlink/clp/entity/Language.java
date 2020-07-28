package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 编程语言实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:31
 */

@Data
@Entity
@Component
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "varchar(32)")
    private String name;

    @Column(columnDefinition = "default true")
    private Boolean enabled;

    @Column(columnDefinition = "float(4,1)")
    private Float time_factor;

    @Column(columnDefinition = "float(4,1)")
    private Float memory_factor;

}
