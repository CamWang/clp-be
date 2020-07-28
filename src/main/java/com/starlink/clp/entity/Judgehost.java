package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 判题机实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:35
 */

@Data
@Entity
@Component
public class Judgehost {

    @Id
    @Column(columnDefinition = "varchar(64)")
    private String name;

    @Column(columnDefinition = "default true")
    private Boolean enabled;

}
