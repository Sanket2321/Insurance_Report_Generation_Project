package com.report.Service;

import java.util.List;

import com.report.Request.SearchRequest;
import com.report.entity.CitizenPlan;

public interface ReportService {
	public List<String> getPlanName();

	public List<String> getPlanStatuses();

	public List<CitizenPlan> search(SearchRequest request);

	public boolean exportExcel();

	public boolean exportPdf();

}
