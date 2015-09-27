package com.edulix.beans;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.edulix.beans.AcadDetails;
import com.edulix.beans.AppliedUni;

@Entity
@Table(name = "STUDENT", catalog = "EDULIX")
public class Student implements Serializable{
	//chk for year,branch
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STUDENT_ID", unique = true, nullable = false)
	private Integer studentId;
	
	@Column(name = "ATTENDING_UNI")
	private String attendingUni;
	
	@Column(name = "TERM")
	private String term;
	
	@Column(name = "PROGRAM")
	private String program;
	
	@Transient
	private AcadDetails acadDetails;
	
	@Column(name = "WORK_EX")
	private String workEx;
	
	@Column(name = "MISC")
	private String miscellaneousDetails;
	
	@Transient
	private ArrayList<AppliedUni> appliedUni;
	
	public Student(){
		
	}
	public Student(String attendingUni, String term, String program,
			AcadDetails acadDetails, String workEx, String miscellaneousDetails,
			ArrayList<AppliedUni> appliedUni) {
		super();
		this.attendingUni = attendingUni;
		this.term = term;
		this.program = program;
		this.acadDetails = acadDetails;
		this.workEx = workEx;
		this.miscellaneousDetails = miscellaneousDetails;
		this.appliedUni = appliedUni;
	}
	public void setAttendingUni(String attendingUni) {
		this.attendingUni = attendingUni;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public void setAcadDetails(AcadDetails acadDetails) {
		this.acadDetails = acadDetails;
	}
	public void setWorkEx(String workEx) {
		this.workEx = workEx;
	}
	public void setMiscellaneousDetails(String miscellaneousDetails) {
		this.miscellaneousDetails = miscellaneousDetails;
	}
	
	public String getAttendingUni() {
		return attendingUni;
	}
	public String getTerm() {
		return term;
	}
	public String getProgram() {
		return program;
	}
	public AcadDetails getAcadDetails() {
		return acadDetails;
	}
	public String getWorkEx() {
		return workEx;
	}
	public String getMiscellaneousDetails() {
		return miscellaneousDetails;
	}
	public ArrayList<AppliedUni> getAppliedUni() {
		return appliedUni;
	}
	public void setAppliedUni(ArrayList<AppliedUni> appliedUni) {
		this.appliedUni = appliedUni;
	}
	public void showStudentDetails(){
		System.out.println("Will be attending : " + this.attendingUni);
		System.out.println("Term : " + this.term);
		System.out.println("Program : " + this.program);
		System.out.println("Work ex : " + this.workEx);
		System.out.println("AWA : " + this.acadDetails.getAwa());
		System.out.println("GRE Quant : " + this.acadDetails.getGreQuant());
		System.out.println("GRE Verbal : " + this.acadDetails.getGreVerbal());
		System.out.println("TOEFL : " + this.acadDetails.getToefl());
		System.out.println("UnderGrad uni : " + this.acadDetails.getUnderGradUni());
		System.out.println("UnderGrad Dept : " + this.acadDetails.getUnderGradDepartment());
		System.out.println("UnderGrad grade : " + this.acadDetails.getUnderGradGrade());
		System.out.println("UnderGrad grade scale : " + this.acadDetails.getUnderGradGradeScale());
		System.out.println("UnderGrad topper's grade : " + this.acadDetails.getUnderGradTopperGrade());
		System.out.println("Other Details : " + this.miscellaneousDetails);
		System.out.println("Applied to - ");
		for(int i=0;i<this.appliedUni.size();i++){
			AppliedUni appliedUniObject = new AppliedUni();
			appliedUniObject = appliedUni.get(i);
			System.out.println("uni name : " + appliedUniObject.getName());
			System.out.println("decision : " + appliedUniObject.getDecision());
			System.out.println("comments : " + appliedUniObject.getComments());
			System.out.println("-----");
		}
		System.out.println("---------------------------------");
	}
}