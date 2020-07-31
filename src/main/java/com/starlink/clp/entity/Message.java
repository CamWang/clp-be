package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 站内信实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:38
 */

@Data
@Entity
@Component
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 消息内容
    @Column(columnDefinition = "varchar(2048)")
    private String text;



}
