package com.starlink.clp.repository;

import com.starlink.clp.entity.Team;
import com.starlink.clp.projection.team.TeamDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeamRepository extends CrudRepository<Team, Integer>,
        PagingAndSortingRepository<Team, Integer>,
        JpaSpecificationExecutor<Team> {

    Page<TeamDetail> findTeamsBy(Pageable pageable);

}
