package com.starlink.clp.entity;

import com.starlink.clp.validate.Empty;
import com.starlink.clp.view.UserModifiedView;
import com.starlink.clp.view.UserRegisterView;
import com.starlink.clp.view.UserSecurityView;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
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
@DynamicUpdate(value = true)
public class User implements Serializable, UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    @Range(min = 0, max = 2097152, message = "用户ID范围超限")
    @NotNull(groups = {UserModifiedView.class}, message = "用户ID不能为空")
    private Integer id;

    // 用户名 - 登陆凭据
    @Column(columnDefinition = "varchar(128)")
    @NotEmpty(groups = {UserRegisterView.class, UserModifiedView.class}, message = "用户名不能为空")
    @Length(groups = {UserRegisterView.class, UserModifiedView.class}, min = 4, max = 32, message = "用户名长度在4-32字符之间")
    private String username;

    // 密码
    @Column(columnDefinition = "varchar(128)")
    @NotEmpty(groups = {UserRegisterView.class, UserModifiedView.class}, message = "用户名不能为空")
    @Length(groups = {UserRegisterView.class, UserModifiedView.class}, min = 4, max = 32, message = "密码长度在4-32字符之间")
    private String password;

    // 昵称
    @Column(columnDefinition = "varchar(128)")
    @NotEmpty(groups = {UserRegisterView.class}, message = "昵称不能为空")
    @Length(groups = {UserRegisterView.class, UserModifiedView.class}, min = 4, max = 32, message = "昵称长度在4-32字符之间")
    private String nickname;

    // 电子邮件
    @Column(columnDefinition = "varchar(128)")
    @NotEmpty(groups = {UserRegisterView.class}, message = "电子邮箱不能为空")
    @Email(groups = {UserRegisterView.class}, message = "电子邮箱格式不正确")
    private String email;

    // 学号
    @Column(columnDefinition = "varchar(128)")
    @Length(min = 4, max = 32, message = "学号长度在4-32之间")
    private String studentId;

    // 电话号
    @Column(columnDefinition = "varchar(128)")
    @Length(min = 4, max = 32, message = "电话号长度在4-32之间")
    private String phone;

    // 头像位置/URL
    @Column(columnDefinition = "varchar(255)")
    @Null(groups = {UserSecurityView.class}, message = "安全检查失败")
    private String avatar;

    // 角色 - Spring Security定义
    @Column(columnDefinition = "varchar(32)")
    @Null(groups = {UserSecurityView.class}, message = "安全检查失败")
    private String role;

    // 是否启用账户 - 账户审核
    @Column(columnDefinition = "tinyint default 1")
    @Null(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Boolean enabled;

    // 是否锁定账户 - 账户惩罚
    @Column(columnDefinition = "tinyint default 0")
    @Null(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Boolean locked;

    // 是否撤销账户发言权力 - 账户惩罚
    @Column(columnDefinition = "tinyint default 0")
    @Null(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Boolean silenced;

    // 上次登录的IP地址
    @Column(columnDefinition = "varchar(128)")
    @Null(groups = {UserSecurityView.class}, message = "安全检查失败")
    private String ip;

    // 注册时间
    @Column(columnDefinition = "datetime")
    @Null(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Date register;

    // 提交的题解
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Empty(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Collection<Submission> submissions = new ArrayList<>();

    // 粘贴板内容
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Empty(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Collection<ClipBoard> clipBoards = new ArrayList<>();

    // 发送过的消息
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Empty(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Collection<Message> messages = new ArrayList<>();

    // 接收的消息
    @ManyToMany(mappedBy = "receivers", cascade = CascadeType.PERSIST)
    @Empty(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Collection<Message> receivedMessages = new ArrayList<>();

    // 参加的所有比赛
    @ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
    @Empty(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Collection<Contest> joinedContests = new ArrayList<>();

    // 所在队伍
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Team team;

    // 所在学校
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private School school;

    // 所在班级
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Clazz clazz;

    public User() {}

    // 注册用构造函数
    public User(String username, String password, String nickname, String email, String studentId, String phone,  String ip) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.studentId = studentId;
        this.phone = phone;
        this.ip = ip;
        this.register = new Date();
        this.role = "ROLE_USER";
        this.enabled = true;
        this.locked = false;
        this.silenced = false;
    }

    public User(Integer id, String username, String password, String nickname, String email, String studentId, String phone, String ip, Integer schoolId, Integer clazzId, Integer teamId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.studentId = studentId;
        this.phone = phone;
        this.ip = ip;
        this.school.setId(schoolId);
        this.clazz.setId(clazzId);
        this.team.setId(teamId);
    }

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
