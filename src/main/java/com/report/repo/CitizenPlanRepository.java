package com.report.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.report.entity.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer> {
	@Query("Select distinct (planName) from CitizenPlan")
	public List<String> getPlanName();

	@Query("Select distinct (planStatus) from CitizenPlan")
	public List<String> getPlanStatus();

}
