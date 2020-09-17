package com.starlink.clp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    /**
     * @author CamWang
     * @since 2020/9/12
     * 判题结果ENUM
     */
    PH0("PH0", "占位符", "Unknown", false, false),
    PH1("PH1", "占位符", "Unknown", false, false),
    PH2("PH2", "占位符", "Unknown", false, false),
    PH3("PH3", "占位符", "Unknown", false, false),
    AC("AC", "题解正确", "Accepted", false, true),
    PE("PE", "格式错误", "Pending Error", false, false),
    WA("WA", "题解错误", "Wrong Answer", true, false),
    TLE("TLE", "时间超限", "Time Limit Exceeded", true, false),
    MLE("MLE", "内存超限", "Memory Limit Exceeded", true, false),
    OLE("OLE", "输出超限", "Output Limit Exceeded", true, false),
    RE("RE", "运行错误", "Run-Time Error", true, false),
    CE("CE", "编译错误", "Compile Error", false, false),
    SV("SV", "安全检查错误", "Security Violation", false, false);


    private final String name;
    private final String messageCn;
    private final String messageEn;
    private final Boolean penalty;
    private final Boolean solved;
}
