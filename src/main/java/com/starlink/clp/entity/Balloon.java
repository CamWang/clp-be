package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 气球实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:47
 */

@Data
@Entity
@Component
public class Balloon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    @Column(columnDefinition = "tinyint default 0")
    private Boolean finished;

}
