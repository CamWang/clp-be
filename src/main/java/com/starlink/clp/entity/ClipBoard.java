package com.starlink.clp.entity;

import com.starlink.clp.constant.LanguageEnum;
import com.starlink.clp.view.ClipboardCreateView;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    @Null(groups = {ClipboardCreateView.class}, message = "粘贴板创建时不能传入ID")
    private Integer id;

    // 后端自动生成非重复的字串代码
    @Column(columnDefinition = "varchar(32)")
    @Null(groups = {ClipboardCreateView.class}, message = "哈希值由后端生成禁止传入")
    private String name;

    // 语言
    @Column(columnDefinition = "tinyint unsigned")
    @NotNull(groups = {ClipboardCreateView.class}, message = "使用的编程语言不能为空")
    private LanguageEnum language;

    // 粘贴板代码
    @Column(columnDefinition = "text")
    @NotNull(groups = {ClipboardCreateView.class}, message = "粘贴板代码不能为空")
    private String code;

    // 创建粘贴板的用户
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @NotNull(groups = {ClipboardCreateView.class}, message = "粘贴板创建的用户不能为空")
    private User user;

}
