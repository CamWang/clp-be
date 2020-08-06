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

    // 气球是否已被处理
    @Column(columnDefinition = "tinyint default 0")
    private Boolean processed;

    // 气球与提交关联
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "submission_ballon",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn(unique = true)
    )
    private Submission submission;

    // 气球与比赛关联
    @ManyToOne(fetch = FetchType.LAZY)
    private Contest contest;

}
