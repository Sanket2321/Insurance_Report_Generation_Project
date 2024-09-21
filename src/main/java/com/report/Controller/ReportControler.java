package com.report.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.report.Request.SearchRequest;
import com.report.Service.ReportService;
import com.report.entity.CitizenPlan;

@Controller
public class ReportControler {
	@Autowired
	private ReportService service;

	@PostMapping("/search")
	
	// here you used modelattribute suppose we select plan name or status and hit the search button all record disapper
	public String handleSeach(@ModelAttribute("search") SearchRequest request, Model model) {
		System.out.println(request);
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("plans", plans);
		init(model);
		return "index";

	}

	@GetMapping("/")
	public String indexPage(Model model) {
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
