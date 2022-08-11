package in.ashokit.reports;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.ashokit.bindings.response.PlanResponse;

public class ExcelReportGenerator {
	
	public void export(List<PlanResponse> plans, HttpServletResponse response) throws Exception  {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Plans");		
		XSSFRow headerRow	= sheet.createRow(0);
		
//		XSSFCell headerCell0 = headerRow.createCell(0);
//		headerCell0.setCellValue("Plan ID");
//		
//		XSSFCell headerCell1 = headerRow.createCell(1);
//		headerCell1.setCellValue("Holder Name");
//		
//		XSSFCell headerCell2 = headerRow.createCell(2);
//		headerCell2.setCellValue("Holder SSN");
//		
//		XSSFCell headerCell3 = headerRow.createCell(3);
//		headerCell3.setCellValue("Plan Name");
//		
//		XSSFCell headerCell4 = headerRow.createCell(4);
//		headerCell4.setCellValue("Plan Status");
		
		// we are optimizing above code like below.
		
		headerRow.createCell(0).setCellValue("Plan ID");
		headerRow.createCell(1).setCellValue("Holder Name");
		headerRow.createCell(2).setCellValue("Holder SSN");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
				
		for (int i = 0; i < plans.size(); i++ ) {
			PlanResponse plan = plans.get(i);			
			XSSFRow dataRow = sheet.createRow(i+1);
			
//			XSSFCell cell0 = dataRow.createCell(0);
//			cell0.setCellValue(plan.getPlanId());
//			
//			XSSFCell cell1 = dataRow.createCell(1);
//			cell1.setCellValue(plan.getPlanHolderName());
//			
//			XSSFCell cell2 = dataRow.createCell(2);
//			cell2.setCellValue(plan.getPlanHolderSsn());
//			
//			XSSFCell cell3 = dataRow.createCell(3);
//			cell3.setCellValue(plan.getPlanName());
//			
//			XSSFCell cell4 = dataRow.createCell(4);
//			cell4.setCellValue(plan.getPlanStatus());
			
			// we are optimizing above code like below.
			
			dataRow.createCell(0).setCellValue(plan.getPlanId());
			dataRow.createCell(1).setCellValue(plan.getPlanHolderName());
			dataRow.createCell(2).setCellValue(plan.getPlanHolderSsn());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());
			
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
		
		
	}
	

}
