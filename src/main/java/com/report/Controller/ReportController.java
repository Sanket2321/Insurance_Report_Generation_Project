package com.report.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.report.Request.SearchRequest;
import com.report.Service.ReportService;
import com.report.entity.CitizenPlan;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {
	@Autowired
	private ReportService service;
	

	 @GetMapping(value = {"/pdf"})
	    public void pdfExport(HttpServletResponse response) throws Exception {
	        response.setContentType("application/pdf");
	        response.setHeader("Content-Disposition", "attachment; filename=plans.pdf");
	        service.exportPdf(response);
	    }
	
	 @GetMapping(value = {"/excel"})
	    public void excelExport(HttpServletResponse response) throws Exception {
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=plans.xlsx");
	        service.exportExcel(response);
	    }

	@PostMapping("/search")
	
	// here you used modelattribute suppose we select plan name or status and hit the search button all record disapper
	public String handleSeach(@ModelAttribute("search") SearchRequest request, Model model) {
		System.out.println(request);
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("plans", plans);
		init(model);
		return "index";

	}
// used to load the empty page 
	@GetMapping("/")
	public String indexPage(Model model) {
		//when my page is loaded my form is empty 
		model.addAttribute("search", new SearchRequest());

		init(model);

		return "index";

	}

	private void init(Model model) {
		// SearchRequest request =
				model.addAttribute("names", service.getPlanName());
		model.addAttribute("status", service.getPlanStatuses());
	}

}
