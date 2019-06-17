package com.example.demo;

public class restApiVo {
	
	private String name;				//이름
	private String description;		//설명
	private String id;					//프로젝트ID
	private String domain_id;			//도메인이름
	private boolean enabled; 		//활성화됨
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDomain_id() {
		return domain_id;
	}
	public void setDomain_id(String domain_id) {
		this.domain_id = domain_id;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
