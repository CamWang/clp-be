package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 编程语言实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:31
 */

@Data
@Entity
@Component
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 语言名称
    @Column(columnDefinition = "varchar(32)")
    private String name;

    // 语言代号
    @Column(columnDefinition = "tinyint unsigned")
    private Integer code;

    // 是否启用语言
    @Column(columnDefinition = "tinyint default 1")
    private Boolean enabled;

    // 时间倍率 - 性能差语言可能跑的慢一点
    @Column(columnDefinition = "float")
    private Float timeFactor;

    // 内存倍率 - 性能差的语言内存占用多一点
    @Column(columnDefinition = "float")
    private Float memoryFactor;

}
