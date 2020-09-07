package com.starlink.clp.repository;

import com.starlink.clp.entity.Judgehost;
import com.starlink.clp.entity.Team;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JudgehostRepository  extends CrudRepository<Judgehost, Integer>,
        PagingAndSortingRepository<Judgehost, Integer>,
        JpaSpecificationExecutor<Judgehost> {
}
