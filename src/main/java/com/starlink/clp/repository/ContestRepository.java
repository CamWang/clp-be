package com.starlink.clp.repository;

import com.starlink.clp.entity.Contest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContestRepository  extends CrudRepository<Contest, Integer>,
        PagingAndSortingRepository<Contest, Integer>,
        JpaSpecificationExecutor<Contest> {
}
