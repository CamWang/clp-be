package com.starlink.clp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 常量
 *
 * @author CamWang
 * @since 2020/7/29
 */
public class Constant {

    // 语言常量 - 一些奇奇怪怪的语言是给粘贴板功能设置的
    @Getter
    @AllArgsConstructor
    public enum LanguageEnum {
        C(100, "c"),
        CPP(101, "cpp"),
        CS(102, "cs"),
        JAVA(103, "java"),
        PYTHON(104, "python"),
        JAVASCRIPT(105, "javascript"),
        PHP(106, "php"),
        GO(107, "go"),
        SQL(108, "sql"),
        CSS(109, "css"),
        HTML(110, "html"),
        BASH(111, "bash"),
        MARKDOWN(112, "markdown"),
        DOCKERFILE(113, "dockerfile"),
        YAML(114, "yaml"),
        MAKEFILE(115, "makefile"),
        LATEX(116, "latex"),
        JSON(117, "json"),
        DJANGO(118, "django"),
        SHELL(119, "shell"),
        SCALA(120, "scala"),
        PROPERTIES(121, "properties");

        private final Integer key;
        private final String name;
    }

    // 难度分类 - 由低到高
    @Getter
    @AllArgsConstructor
    public enum DifficultyEnum {
        NOOB(0, "noob"),
        BEGINNER(1, "beginner"),
        EASY(2, "easy"),
        NORMAL(3, "normal"),
        HARD(4, "hard"),
        EXPERT(5, "expert"),
        HELL(6, "hell");

        private final Integer level;
        private final String nick;
    }

}
