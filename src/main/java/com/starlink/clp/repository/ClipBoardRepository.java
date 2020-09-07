package com.starlink.clp.repository;

import com.starlink.clp.entity.ClipBoard;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClipBoardRepository extends CrudRepository<ClipBoard, Integer>,
        PagingAndSortingRepository<ClipBoard, Integer>,
        JpaSpecificationExecutor<ClipBoard> {
}
