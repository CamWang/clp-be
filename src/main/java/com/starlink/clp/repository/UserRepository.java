package com.starlink.clp.repository;

import com.starlink.clp.entity.User;
import com.starlink.clp.projection.user.UserInfo;
import com.starlink.clp.projection.user.UserSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, PagingAndSortingRepository<User, Integer>, JpaSpecificationExecutor<User> {
    Page<UserSimple> getAllBy(Pageable pageable);
    UserInfo getFirstById(Integer id);
    Boolean existsUserByUsername(String username);
    User findUserByUsernameAndId(String username, Integer id);
}
