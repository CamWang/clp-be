package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 队伍实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:35
 */

@Data
@Entity
@Component
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "varchar(128)")
    private String name;

    @Column(columnDefinition = "varchar(255)")
    private String description;
}
