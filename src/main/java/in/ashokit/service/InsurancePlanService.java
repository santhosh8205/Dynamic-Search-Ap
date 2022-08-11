package in.ashokit.service;

import java.util.List;

import in.ashokit.bindings.request.SearchRequest;
import in.ashokit.bindings.response.PlanResponse;

public interface InsurancePlanService {
	
	public List<PlanResponse> searchPlans(SearchRequest request);
	
	public List<String> getUniquePlanNames();
	
	public List<String> getUniquePlanStatues();
	

}
