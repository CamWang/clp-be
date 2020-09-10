package com.starlink.clp.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author CamWang
 * @since 2020/9/10 16:03
 */

@Data
@Entity
@Component
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 标题
    @Column(columnDefinition = "varchar(32)")
    @Length(max = 40, message = "新闻标题字数超限")
    private String title;

    // 标签显示内容
    @Column(columnDefinition = "varchar(32)")
    @Length(max = 2000, message = "新闻字数超限")
    private String content;

}
