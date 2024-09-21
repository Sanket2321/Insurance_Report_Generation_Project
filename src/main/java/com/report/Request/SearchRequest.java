package com.report.Request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchRequest {
	private String planName;
	private String planStatus;
	private String Gender;
	private String startDate;
	private String endDate;

	@Override
	public String toString() {
		return "SearchRequest [planName=" + planName + ", planStatus=" + planStatus + ", Gender=" + Gender
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
