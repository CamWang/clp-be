package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * 重判任务实体类
 *
 * @author CamWang
 * @since 2020/7/28 14:44
 */

@Data
@Entity
@Component
public class Rejudge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "datetime(3)")
    private Date start;


}
