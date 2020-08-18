package com.starlink.clp.projection.school;

/**
 * 为本校用户提供本校的基本信息
 *
 * @author Qilin
 * @since 2020/8/15 18:25
 */
public interface SchoolInfo {
    Integer getId();

    String getName();

    String getDescription();

    String getAvatar();
}
