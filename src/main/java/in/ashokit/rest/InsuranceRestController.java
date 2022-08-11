package in.ashokit.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.request.SearchRequest;
import in.ashokit.bindings.response.PlanResponse;
import in.ashokit.reports.ExcelReportGenerator;
import in.ashokit.reports.PdfReportGenerator;
import in.ashokit.service.InsurancePlanServiceImpl;

@RestController
public class InsuranceRestController {
	
	@Autowired
	private InsurancePlanServiceImpl service;
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		
      response.setContentType("application/pdf");
      String headerKey = "Content-Disposition";
      String headerValue = "attachment; filename=plans.pdf";
      response.setHeader(headerKey, headerValue);
		
		List<PlanResponse> plans = service.searchPlans(null);
		PdfReportGenerator pdfReport = new PdfReportGenerator();
		//pdfReport.exportPdf(plans, response);
		pdfReport.exportPdf(plans, response);
	}
		
	
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=plans_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
        // in below code we removed time. instead of above 8 lines we can write below 4 lines
        
//        response.setContentType("application/octet-stream");
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=plans.xlsx";
//        response.setHeader(headerKey, headerValue);
        
		
		List<PlanResponse> plans = service.searchPlans(null);
		ExcelReportGenerator excelReport = new ExcelReportGenerator();
		excelReport.export(plans, response);
	}
		
	
//	@GetMapping("/plans")
//	public ResponseEntity<List<PlanResponse>> getPlans(SearchRequest request) {		
//		List<PlanResponse> searchPlans = service.searchPlans(request);		
//		return new ResponseEntity<>(searchPlans, HttpStatus.OK);
	
	// if our data is secure we don't want to expose data in URL then go for post mapping.
		
	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> searchPlan(@RequestBody SearchRequest request) {		
		List<PlanResponse> searchPlans = service.searchPlans(request);		

		return new ResponseEntity<>(searchPlans, HttpStatus.OK);
	
	}
	
	@GetMapping("/plannames")
	public ResponseEntity<List<String>> getPlanNames() {
		   List<String> planNames = service.getUniquePlanNames();
		   return new ResponseEntity<>(planNames, HttpStatus.OK);		
	}
	
	
	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getPlanStatus() {
		   List<String> status = service.getUniquePlanStatues();
		   return new ResponseEntity<>(status, HttpStatus.OK);		
	}

}
