package com.edulix.scrapper;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.edulix.beans.AppliedUni;
import com.edulix.beans.Student;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SaveData {

	private DataOutputStream dos;
	private String fileName;
	private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
    	// Close caches and connection pools
    	getSessionFactory().close();
    }

	public SaveData(String fileName){
		try {
			this.fileName = fileName;
			throw new FileNotFoundException();
			//dos = new DataOutputStream(new FileOutputStream(new File(fileName)));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToFile(Student student) throws IOException{
		dos.writeBytes("\n");
		dos.writeBytes("Will be attending : " + student.getAttendingUni());
		dos.writeBytes("\n");
		dos.writeBytes("Term : " + student.getTerm());
		dos.writeBytes("\n");
		dos.writeBytes("Program : " + student.getProgram());
		dos.writeBytes("\n");
		dos.writeBytes("Work ex : " + student.getWorkEx());
		dos.writeBytes("\n");
		dos.writeBytes("AWA : " + student.getAcadDetails().getAwa());
		dos.writeBytes("\n");
		dos.writeBytes("GRE Quant : " + student.getAcadDetails().getGreQuant());
		dos.writeBytes("\n");
		dos.writeBytes("GRE Verbal : " + student.getAcadDetails().getGreVerbal());
		dos.writeBytes("\n");
		dos.writeBytes("TOEFL : " + student.getAcadDetails().getToefl());
		dos.writeBytes("\n");
		dos.writeBytes("UnderGrad uni : " + student.getAcadDetails().getUnderGradUni());
		dos.writeBytes("\n");
		dos.writeBytes("UnderGrad Dept : " + student.getAcadDetails().getUnderGradDepartment());
		dos.writeBytes("\n");
		dos.writeBytes("UnderGrad grade : " + student.getAcadDetails().getUnderGradGrade());
		dos.writeBytes("\n");
		dos.writeBytes("UnderGrad grade scale : " + student.getAcadDetails().getUnderGradGradeScale());
		dos.writeBytes("\n");
		dos.writeBytes("UnderGrad topper's grade : " + student.getAcadDetails().getUnderGradTopperGrade());
		dos.writeBytes("\n");
		dos.writeBytes("Other Details : " + student.getMiscellaneousDetails());
		dos.writeBytes("\n");
		dos.writeBytes("Applied to - ");
		dos.writeBytes("\n");
		for(int i=0;i<student.getAppliedUni().size();i++){
			AppliedUni appliedUniObject = new AppliedUni();
			appliedUniObject = student.getAppliedUni().get(i);
			dos.writeBytes("\n");
			dos.writeBytes("uni name : " + appliedUniObject.getName());
			dos.writeBytes("\n");
			dos.writeBytes("decision : " + appliedUniObject.getDecision());
			dos.writeBytes("\n");
			dos.writeBytes("comments : " + appliedUniObject.getComments());
			dos.writeBytes("\n");
			dos.writeBytes("-----");
		}
		dos.writeBytes("\n");
		dos.writeBytes("---------------------------------");
		dos.writeBytes("\n");
	}
	
	public void writeToDatabase(Student student){
		Session session = SaveData.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
	}
	
	public void exportPDF() throws IOException{
		File file = new File(fileName);
		FileInputStream fis=null;  
        DataInputStream in=null;  
        InputStreamReader isr=null;  
        BufferedReader br=null;  
        try {  
            Document pdfDoc = new Document();  
            String output_file =file.getParent()+"/"+"output.pdf";  
            PdfWriter writer=PdfWriter.getInstance(pdfDoc,new FileOutputStream(output_file));  
            pdfDoc.open();  
            pdfDoc.setMarginMirroring(true);  
            pdfDoc.setMargins(36, 72, 108,180);  
            pdfDoc.topMargin();  
            Font myfont = new Font();  
            Font bold_font = new Font();  
            bold_font.setStyle(Font.BOLD);  
            bold_font.setSize(10);  
            myfont.setStyle(Font.NORMAL);  
            myfont.setSize(10);  
            pdfDoc.add(new Paragraph("\n"));  
            if(file.exists()){  
                fis = new FileInputStream(file);  
                in = new DataInputStream(fis);  
                isr=new InputStreamReader(in);  
                br = new BufferedReader(isr);  
                String strLine;  
                while ((strLine = br.readLine()) != null)  {  
                    Paragraph para =new Paragraph(strLine+"\n",myfont);  
                    para.setAlignment(Element.ALIGN_JUSTIFIED);  
                    pdfDoc.add(para);  
                }  
            }     
            else  
                System.out.println("no such file exists!");  
            pdfDoc.close();   
        }  
        catch(Exception e) {  
            System.out.println("Exception: " + e.getMessage());  
        }  
        finally {  
            if(br!=null)  
                br.close();
            if(fis!=null)  
                fis.close();  
            if(in!=null)  
                in.close();  
            if(isr!=null)  
                isr.close();  
        }  
	}
	public static void main(String agrs[]) throws IOException{
		SaveData s = new SaveData("/home/danish-shark/workspace/Scrapper/output/MS_CS.txt");
		s.exportPDF();
	}
}