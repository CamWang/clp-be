package com.starlink.clp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
@Component
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 队伍名称
    @Column(columnDefinition = "varchar(128)")
    private String name;

    // 队伍描述
    @Column(columnDefinition = "varchar(255)")
    private String description;

    // 队伍创建时间
    @Column(columnDefinition = "datetime")
    private Date register;

}
