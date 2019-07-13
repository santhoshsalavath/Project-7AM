package com.usa.fedral.gov.ssa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.usa.fedral.gov.ssa.entity.StateEntity;

@Repository
public interface StateMasterRepository extends JpaRepository<StateEntity,Integer> {

	@Query(value = "SELECT state_name FROM state_master ",nativeQuery = true)
	public List<String []> findStatesNames();
}
