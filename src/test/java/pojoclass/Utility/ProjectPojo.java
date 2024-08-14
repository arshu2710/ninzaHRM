package pojoclass.Utility;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectPojo {
	 String ProjectName;
	 String status;
	 String createdBy;
	 int teamSize;
	 public ProjectPojo () {}
	public ProjectPojo(String projectName, String status, String createdBy, int teamSize) {
	
		this.ProjectName = projectName;
		this.status = status;
		this.createdBy = createdBy;
		this.teamSize = teamSize;
	}
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	 
	
}
