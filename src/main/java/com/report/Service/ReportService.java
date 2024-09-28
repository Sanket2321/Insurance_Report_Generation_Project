package com.report.Service;

import java.util.List;

import com.report.Request.SearchRequest;
import com.report.entity.CitizenPlan;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	public List<String> getPlanName();

	public List<String> getPlanStatuses();

	public List<CitizenPlan> search(SearchRequest request);

	
	
  //we take response because we send the file to browser
	public boolean exportExcel(HttpServletResponse response) throws Exception;

	public boolean exportPdf(HttpServletResponse response) throws Exception;



}
