package com.nt.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.Entity.ApplicationEntity;
import com.nt.Entity.IESUsersEntity;


public interface IESApplicationRepo extends JpaRepository<ApplicationEntity, Integer> {

	
	
	@Query("SELECT a FROM ApplicationEntity a WHERE a.createdByApps = :entity")
	List<ApplicationEntity> findByCreatedByApps(IESUsersEntity entity);

	
}
