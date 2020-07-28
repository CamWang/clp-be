package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author CamWang
 * @since 2020/7/28 18:08
 */

@Data
@Entity
@Component
public class ClipBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "varchar(32)")
    private String name;

    @Column(columnDefinition = "varchar(32)")
    private String language;

    @Column(columnDefinition = "text")
    private String code;

}
