package com.momentum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.momentum.entity.AuditTable;
import com.momentum.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

	@Query(value = "select * FROM users WHERE username = :username and password=:password",nativeQuery = true)
	User validateLogin(@Param(value = "username")String username,@Param(value = "password")String password);
	
	@Query(value = "select * FROM users WHERE username = :username",nativeQuery = true)
	List<User> findByUsername(@Param(value = "username")String username);
}
