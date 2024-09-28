package com.report.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.report.entity.CitizenPlan;
import com.report.repo.CitizenPlanRepository;

import jakarta.servlet.http.HttpServletResponse;
@Component
public class PdfGenerator {
	@Autowired
	private CitizenPlanRepository planRepo;
	public void generate(HttpServletResponse response,File f1) throws Exception
	{
		
		Document document = new Document(PageSize.A4);

		// whatever document we created i want to attach this document response object
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f1));   //this code for i want to  attach pdf file to email
		document.open();
		Font fonttitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fonttitle.setSize(20);
		Paragraph p = new Paragraph("citizen plan info", fonttitle);
		p.setAlignment(p.ALIGN_CENTER);
		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setSpacingBefore(5);

		table.addCell("ID");
		table.addCell("Citizen name");
		table.addCell("Plan name");
		table.addCell("plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		List<CitizenPlan> plans = planRepo.findAll();
		for (CitizenPlan citizenPlan : plans) {
			table.addCell(String.valueOf(citizenPlan.getCitizenid()));
			table.addCell(citizenPlan.getCitizenname());
			table.addCell(citizenPlan.getPlanName());
			table.addCell(citizenPlan.getPlanStatus());
			if (null != citizenPlan.getPlanStartDate()) {
				table.addCell(citizenPlan.getPlanStartDate() + "");
			} else {
				table.addCell("N/A");
			}
			if (null != citizenPlan.getPlanEndDate()) {
				table.addCell(citizenPlan.getPlanEndDate() + "");
			} else {
				table.addCell("N/A");
			}
		}
		document.add(table);
		document.close();

		
	}

}
