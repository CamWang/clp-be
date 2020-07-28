package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 站内信实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:38
 */

@Data
@Entity
@Component
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "text")
    private String text;


}
