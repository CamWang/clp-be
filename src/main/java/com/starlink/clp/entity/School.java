package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 学校实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:37
 */

@Data
@Entity
@Component
public class School implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 学校名称
    @Column(columnDefinition = "varchar(128)")
    private String name;

    // 学校描述
    @Column(columnDefinition = "varchar(255)")
    private String description;

    // 学校图片
    @Column(columnDefinition = "varchar(255)")
    private String avatar;

}
