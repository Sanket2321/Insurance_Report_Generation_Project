package com.report.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.report.Request.SearchRequest;
import com.report.entity.CitizenPlan;
import com.report.repo.CitizenPlanRepository;
import com.report.util.EmailUtil;
import com.report.util.ExcelGenerator;
import com.report.util.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceIMPL implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepo;
	@Autowired
	private ExcelGenerator excelGenerator;
	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private EmailUtil emailUtil;

	@Override
	public List<String> getPlanName() {
		return planRepo.getPlanName();
	}

	@Override
	public List<String> getPlanStatuses() {

		return planRepo.getPlanStatus();
		// 25:46
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}

		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}

		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}

		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);
		}

		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate1 = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate1);
		}
		// example of query
		return planRepo.findAll(Example.of(entity));
	}

	@Override

	// which api you used to generate excel file
	// --> apache poi api we are using to generate excel file in java
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		File f = new File("plans.xlsx");
		List<CitizenPlan> plans = planRepo.findAll();
		excelGenerator.generate(response, f);
		String subject = "test mail subject";
		String body = "<h1>Test mail body</h1>";
		String to = "sonawanesanket2197@gmail.com";

		emailUtil.sendEmail(subject, body, to, f);
		f.delete();// from my system my file should be deleted it go should in attachment then it
					// dowmloaded in browser then deleted from my server that mean my folder

		return true;
	}

	// for pdf we used itext pdf/ open pdf / aspose dependepecy
	// we used itext pdf
	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		File f1=new File("plans.pdf");  //i am creating pdf file 

		//List<CitizenPlan> plans = planRepo.findAll();
		pdfGenerator.generate(response,f1);// i am passing this pdf file data to generate method and it will be create file in local system then
		String subject = "test mail subject";
		String body = "<h1>Test mail body</h1>";
		String to = "sonawanesanket2197@gmail.com";

		emailUtil.sendEmail(subject, body, to, f1);  // i am sending same file in the attachment in email 
		f1.delete();  // then i am deleting from local machinr or from folder
		return true;
	}

}