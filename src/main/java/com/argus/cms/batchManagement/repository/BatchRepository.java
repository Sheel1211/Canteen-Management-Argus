package com.argus.cms.batchManagement.repository;

import com.argus.cms.batchManagement.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batch,Long> {
}
