package com.edulix.beans;

import java.io.Serializable;

public class AcadDetails implements Serializable{
	private String awa;
	private String toefl;
	private String greQuant;
	private String greVerbal;
	private String underGradUni;
	private String underGradDepartment;
	private String underGradGrade;
	private String underGradTopperGrade;
	private String underGradGradeScale;
	
	public AcadDetails(String awa, String toefl, String greQuant,
			String greVerbal, String underGradUni, String underGradDepartment,
			String underGradGrade, String underGradTopperGrade,
			String underGradGradeScale) {
		this.awa = awa;
		this.toefl = toefl;
		this.greQuant = greQuant;
		this.greVerbal = greVerbal;
		this.underGradUni = underGradUni;
		this.underGradDepartment = underGradDepartment;
		this.underGradGrade = underGradGrade;
		this.underGradTopperGrade = underGradTopperGrade;
		this.underGradGradeScale = underGradGradeScale;
	}
	
	public String getAwa() {
		return awa;
	}

	public String getToefl() {
		return toefl;
	}

	public String getGreQuant() {
		return greQuant;
	}

	public String getGreVerbal() {
		return greVerbal;
	}

	public String getUnderGradUni() {
		return underGradUni;
	}

	public String getUnderGradDepartment() {
		return underGradDepartment;
	}

	public String getUnderGradGrade() {
		return underGradGrade;
	}

	public String getUnderGradTopperGrade() {
		return underGradTopperGrade;
	}

	public String getUnderGradGradeScale() {
		return underGradGradeScale;
	}

	public void setAwa(String awa) {
		this.awa = awa;
	}

	public void setToefl(String toefl) {
		this.toefl = toefl;
	}

	public void setGreQuant(String greQuant) {
		this.greQuant = greQuant;
	}

	public void setGreVerbal(String greVerbal) {
		this.greVerbal = greVerbal;
	}

	public void setUnderGradUni(String underGradUni) {
		this.underGradUni = underGradUni;
	}

	public void setUnderGradDepartment(String underGradDepartment) {
		this.underGradDepartment = underGradDepartment;
	}

	public void setUnderGradGrade(String underGradGrade) {
		this.underGradGrade = underGradGrade;
	}

	public void setUnderGradTopperGrade(String underGradTopperGrade) {
		this.underGradTopperGrade = underGradTopperGrade;
	}

	public void setUnderGradGradeScale(String underGradGradeScale) {
		this.underGradGradeScale = underGradGradeScale;
	}
}
