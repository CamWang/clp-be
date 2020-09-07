package com.starlink.clp.repository;

import com.starlink.clp.entity.Executable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExecutableRepository  extends CrudRepository<Executable, Integer>,
        PagingAndSortingRepository<Executable, Integer>,
        JpaSpecificationExecutor<Executable> {
}
