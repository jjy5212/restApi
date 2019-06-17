package com.example.demo;

public class restApiDetailVo {
	
	private String name;				//프로젝트 이름
	private String id;					//프로젝트 id
	private boolean enabled; 		//활성화됨	
	private String description;		//설명
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}
