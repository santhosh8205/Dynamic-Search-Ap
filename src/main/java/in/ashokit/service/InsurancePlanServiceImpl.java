package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.request.SearchRequest;
import in.ashokit.bindings.response.PlanResponse;
import in.ashokit.entity.InsurancePlanEntity;
import in.ashokit.repository.InsurancePlanRepository;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {
	
	@Autowired
	private InsurancePlanRepository repo;
	
	@Override
	public List<PlanResponse> searchPlans(SearchRequest request) {
		
		
		        InsurancePlanEntity entity = new InsurancePlanEntity();
		        
		        if(request!=null && request.getPlanName() != null && !request.getPlanName().equals("")) {
		        	entity.setPlanName(request.getPlanName());
		        }
		        
		        if(request!=null && request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
		        	entity.setPlanStatus(request.getPlanStatus());
		        }
		        
		        Example<InsurancePlanEntity> of = Example.of(entity);
		        List<InsurancePlanEntity> findAll = repo.findAll(of);
		
		                  List<PlanResponse> plans = new ArrayList<>();
		                  for(InsurancePlanEntity plan : findAll) {
		                	  PlanResponse presp = new PlanResponse();
		                	  BeanUtils.copyProperties(plan, presp);
		                	  plans.add(presp);
		                	  
		                  }		        		        		        
		return plans;
	}
	
	@Override
	public List<String> getUniquePlanNames() {
		
		return repo.getPlanNames();
	}
	
	@Override
	public List<String> getUniquePlanStatues() {
		
		return repo.getPlanStatus();
	}

}
