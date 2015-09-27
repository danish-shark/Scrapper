package com.edulix.beans;

import java.io.Serializable;

public class AppliedUni implements Serializable{
	private String name;
	private String comments;
	private String decision;
	
	public AppliedUni(){
		
	}
	public AppliedUni(String name, String comments, String decision) {
		this.name = name;
		this.comments = comments;
		this.decision = decision;
	}
	
	public String getName() {
		return name;
	}

	public String getComments() {
		return comments;
	}

	public String getDecision() {
		return decision;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}
}
