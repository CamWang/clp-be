package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

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

    // 消息发送者
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 发送给某场比赛参赛队员的消息
    @ManyToOne(fetch = FetchType.LAZY)
    private Contest contest;

    // 消息接收者
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_message",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    private Collection<User> receivers;

}
