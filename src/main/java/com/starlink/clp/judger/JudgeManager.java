package com.starlink.clp.judger;

import com.starlink.clp.entity.Submission;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author CamWang
 * @since 2020/9/12 15:49
 */
public class JudgeManager {

    private final ConcurrentLinkedQueue<Judger> judgers = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Submission> submissions = new ConcurrentLinkedQueue<>();


}
