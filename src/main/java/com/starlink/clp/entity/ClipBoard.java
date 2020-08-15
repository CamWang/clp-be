package com.starlink.clp.entity;

import com.starlink.clp.constant.LanguageEnum;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author CamWang
 * @since 2020/7/28 18:08
 */

@Data
@Entity
public class ClipBoard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 后端自动生成非重复的字串代码
    @Column(columnDefinition = "varchar(32)")
    private String name;

    // 语言
    @Column(columnDefinition = "tinyint unsigned")
    private LanguageEnum language;

    // 粘贴板代码
    @Column(columnDefinition = "text")
    private String code;

    // 创建粘贴板的用户
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user;

}
