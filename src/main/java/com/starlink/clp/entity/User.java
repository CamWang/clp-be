package com.starlink.clp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author CamWang
 * @since 2020/7/28 11:25
 */

@Data
@Entity
@Component
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "unsigned int")
    private Integer id;

    @Column(columnDefinition = "varchar(64)")
    private String username;

    @Column(columnDefinition = "varchar(128)")
    private String password;

    @Column(columnDefinition = "varchar(32)")
    private String role;

    @Column(columnDefinition = "default true")
    private Boolean enabled;

    @Column(columnDefinition = "default false")
    private Boolean locked;

    @Column(columnDefinition = "default false")
    private Boolean silenced;

    @Column(columnDefinition = "varchar(128)")
    private String nickname;

    @Column(columnDefinition = "varchar(128)")
    private String email;

    @Column(columnDefinition = "varchar(128)")
    private String ip;

    @Column(columnDefinition = "varchar(255)")
    private String avatar;

    @Column(columnDefinition = "datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date register;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
