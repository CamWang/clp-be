package com.starlink.clp.security;

import lombok.Data;

import java.util.Date;

/**
 * @author CamWang
 * @since 2020/9/8 10:15
 */
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private String studentId;
    private String phone;
    private String avatar;
    private String role;
    private Boolean silenced;
    private String ip;
    private Date register;
}
