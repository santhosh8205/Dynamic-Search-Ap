package in.ashokit.bindings.response;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class PlanResponse {

	private Integer planId;
	private String planName;	
	private String planHolderName;	
	private Long planHolderSsn;	
	private String planStatus;
	
}
