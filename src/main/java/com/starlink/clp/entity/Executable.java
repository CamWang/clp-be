package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 脚本实体类
 * 在编译题解、判题过程中比赛组织者自定义的执行脚本
 *
 * @author CamWang
 * @since 2020/7/28 14:20
 */

@Data
@Entity
@Component
public class Executable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 脚本标题
    @Column(columnDefinition = "varchar(64)")
    private String title;

    // 脚本类型
    @Column(columnDefinition = "varchar(16)")
    private String type;

    // 脚本文件位置
    @Column(columnDefinition = "varchar(128)")
    private String file;

}
