package com.starlink.clp.repository;

import com.starlink.clp.entity.Contest;
import com.starlink.clp.projection.contest.ContestDetail;
import com.starlink.clp.projection.contest.ContestSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepository  extends CrudRepository<Contest, Integer>,
        PagingAndSortingRepository<Contest, Integer>,
        JpaSpecificationExecutor<Contest> {
    Page<ContestSimple> findAllByEnabledIsTrue(Pageable pageable);
    ContestDetail findFirstByIdAndEnabledIsTrue(Integer id);
}
