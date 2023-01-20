package com.momentum.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momentum.entity.AuditTable;

@Repository
public interface AuditRepository extends JpaRepository<AuditTable, Long>{

	
	@Query(value = "select * FROM audit WHERE user = :username",nativeQuery = true)
	List<AuditTable> findByUsername(@Param(value = "username")String username);
	
	@Query(value = "select * FROM audit WHERE created_date between '2023-01-01' AND '2023-01-04'",nativeQuery = true)
	AuditTable findByDateRange();
	
	
}
