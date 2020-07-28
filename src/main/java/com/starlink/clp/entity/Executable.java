package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 脚本实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:20
 */

@Data
@Entity
@Component
public class Executable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "varchar(64)")
    private String title;

    @Column(columnDefinition = "varchar(16)")
    private String type;

    @Column(columnDefinition = "varchar(128)")
    private String file;

}
