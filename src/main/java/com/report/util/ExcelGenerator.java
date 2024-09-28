package com.report.util;

import java.io.FileOutputStream;
import java.util.List;
import java.io.File;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.report.entity.CitizenPlan;
import com.report.repo.CitizenPlanRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ExcelGenerator {
	// it is optional we can called by passing
	@Autowired
	private CitizenPlanRepository planRepo;

	public void generate(HttpServletResponse response,File file) throws Exception {
		// HSSFWorkbook is for .xls files (Excel 97-2003).
		// If you want to create .xlsx files (Excel 2007 and later), use XSSFWorkbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		var sheet = workbook.createSheet("plans_data");

		var headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan name");
		headerRow.createCell(3).setCellValue("Plan Status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amount");

		List<CitizenPlan> records = planRepo.findAll();
		int dataRowIndex = 1;

		for (CitizenPlan citizenPlan : records) {
			var dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(citizenPlan.getCitizenid());
			dataRow.createCell(1).setCellValue(citizenPlan.getCitizenname());
			dataRow.createCell(2).setCellValue(citizenPlan.getPlanName());
			dataRow.createCell(3).setCellValue(citizenPlan.getPlanStatus());
			if (null != citizenPlan.getPlanStartDate()) {
				dataRow.createCell(4).setCellValue(citizenPlan.getPlanStartDate() + "");

			} else {
				dataRow.createCell(4).setCellValue("N/A");

			}

			if (null != citizenPlan.getPlanEndDate()) {
				dataRow.createCell(5).setCellValue(citizenPlan.getPlanEndDate() + "");
			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}

			if (citizenPlan.getBenefitAmt() != null) {
				dataRow.createCell(6).setCellValue(citizenPlan.getBenefitAmt());
			} else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			dataRowIndex++;
		}
		//this fileoutputstream create file in current folder
		
		//FileOutputStream fos=new FileOutputStream(new File("plans.xlsx"));
		FileOutputStream fos=new FileOutputStream(file);
		
		workbook.write(fos);
		fos.close();
		
		
		

		// we send the file to browser for this response
    // this serveletoutputStream send file to the browser
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

	}

}
