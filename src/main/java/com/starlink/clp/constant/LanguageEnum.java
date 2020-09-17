package com.starlink.clp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LanguageEnum {
    C("c", "c"),                // 0
    CPP("cpp", "cc"),           // 1
    PASCAL("pascal", "pas"),    // 2
    JAVA("java", "java"),       // 3
    RUBY("ruby", "rb"),         // 4
    BASH("bash", "sh"),
    PYTHON("python", "py"),     // 6
    PHP("php", "php"),
    PERL("perl", "pl"),
    CS("cs", "cs"),             // 9
    OBJC("objective-c", "m"),
    FREEBASIC("freebasic", "bas"),
    SCHEME("scheme", "scm"),    // 12
    CLANG("clang", "c"),
    CLANGPP("clangpp", "cc"),
    LUA("lua", "lua"),          // 15
    JAVASCRIPT("javascript", "js"), // 16
    GO("go", "go"),             // 17
    SQL("sql", "sql"),
    CSS("css", ""),
    HTML( "html", ""),
    MARKDOWN("markdown", ""),
    DOCKERFILE("dockerfile", ""),
    YAML("yaml", ""),
    MAKEFILE("makefile", ""),
    LATEX("latex", ""),
    JSON("json", ""),
    DJANGO("django", ""),
    SHELL("shell", ""),
    SCALA("scala", ""),
    PROPERTIES("properties", "");

    private final String name;
    private final String command;
}
