package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 判题机实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:35
 */

@Data
@Entity
@Component
public class Judgehost implements Serializable {

    // 判题机名称
    @Id
    @Column(columnDefinition = "varchar(64)")
    private String name;

    // 是否启用该判题机
    @Column(columnDefinition = "tinyint default 1")
    private Boolean enabled;

}
