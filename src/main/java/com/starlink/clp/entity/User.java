package com.starlink.clp.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * 用户实体类
 *
 * @author CamWang
 * @since 2020/7/28 11:25
 */

@Data
@Entity
@Component
public class User implements Serializable, UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Integer id;

    // 用户名 - 登陆凭据
    @Column(columnDefinition = "varchar(64)")
    private String username;

    // 密码
    @Column(columnDefinition = "varchar(128)")
    private String password;

    // 昵称
    @Column(columnDefinition = "varchar(128)")
    private String nickname;

    // 电子邮件
    @Column(columnDefinition = "varchar(128)")
    private String email;

    // 学号
    @Column(columnDefinition = "varchar(128)")
    private String studentId;

    // 电话号
    @Column(columnDefinition = "varchar(128)")
    private String phone;

    // 头像位置/URL
    @Column(columnDefinition = "varchar(255)")
    private String avatar;

    // 角色 - Spring Security定义
    @Column(columnDefinition = "varchar(32)")
    private String role;

    // 是否启用账户 - 账户审核
    @Column(columnDefinition = "tinyint default 1")
    private Boolean enabled;

    // 是否锁定账户 - 账户惩罚
    @Column(columnDefinition = "tinyint default 0")
    private Boolean locked;

    // 是否撤销账户发言权力 - 账户惩罚
    @Column(columnDefinition = "tinyint default 0")
    private Boolean silenced;

    // 上次登录的IP地址
    @Column(columnDefinition = "varchar(128)")
    private String ip;

    // 注册时间
    @Column(columnDefinition = "datetime")
    private Date register;

    // 获取权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.role));
    }

    // 账户是否过期 - 默认永不过期
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    // 账户是否锁定 - 根据数据库判断
    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    // 账户的密码是否过期 - 默认永不过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否可用 - 根据数据库判断
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
