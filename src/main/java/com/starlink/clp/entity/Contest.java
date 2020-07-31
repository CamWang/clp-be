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
 * 比赛实体类
 *
 * @author CamWang
 * @since 2020/7/28 13:38
 */

@Data
@Entity
@Component
public class Contest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    @Column(columnDefinition = "varchar(128)")
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "tinyint unsigned")
    private Integer penalty;

    @Column(columnDefinition = "tinyint default 1")
    private Boolean privately;

    // 是否向用户展示错误
    @Column(columnDefinition = "tinyint default 0")
    private Boolean error;

    @Column(columnDefinition = "datetime")
    private Date start;

    @Column(columnDefinition = "datetime")
    private Date end;

    @Column(columnDefinition = "datetime")
    private Date finish;

    @Column(columnDefinition = "tinyint default 0")
    private Boolean enabled;


}
