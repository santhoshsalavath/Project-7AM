package com.usa.fedral.gov.ssa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usa.fedral.gov.ssa.entity.SSNEntity;

@Repository
public interface SSNMasterRepository extends JpaRepository<SSNEntity,Integer> {

}
