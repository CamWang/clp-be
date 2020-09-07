package com.starlink.clp.repository;

import com.starlink.clp.entity.Message;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends CrudRepository<Message, Integer>,
        PagingAndSortingRepository<Message, Integer>,
        JpaSpecificationExecutor<Message> {
}
