package com.starlink.clp.entity;

import com.starlink.clp.view.MessageCreateView;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    @Null(groups = {MessageCreateView.class}, message = "创建信息时不能传入ID")
    private Integer id;

    // 消息内容
    @Column(columnDefinition = "varchar(2048)")
    @NotNull(groups = {MessageCreateView.class}, message = "不能创建空内容消息")
    @Range(groups = {MessageCreateView.class}, min = 1, max = 2048, message = "消息过长或过短")
    private String text;

    // 消息发送者
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @NotNull(groups = {MessageCreateView.class}, message = "发送人不能为空")
    private User user;

    // 发送给某场比赛参赛队员的消息
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Contest contest;

    // 消息接收者
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_message",
            joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn
    )
    @NotNull(groups = {MessageCreateView.class}, message = "接收人不能为空")
    private Collection<User> receivers;

}
