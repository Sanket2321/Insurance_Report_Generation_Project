package com.report.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class CitizenPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer citizenid;
	private String citizenname;
	private String gender;
	private String planName;
	private String planStatus;
	private LocalDate PlanStartDate;
	private LocalDate PlanEndDate;
	private Double benefitAmt;
	private String denialReason;
	private String terminationReason;
	private LocalDate terminatedDate;

}
