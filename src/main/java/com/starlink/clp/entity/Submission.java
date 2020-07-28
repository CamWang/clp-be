package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * 交题记录实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:29
 */
@Data
@Entity
@Component
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "datetime(3)")
    private Date submit;

    @Column(columnDefinition = "varchar(32)")
    private String result;

    @Column(columnDefinition = "text")
    private String code;

}
