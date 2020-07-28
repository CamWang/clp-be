package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 气球实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:47
 */

@Data
@Entity
@Component
public class Balloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "default false")
    private Boolean done;

}
